package com.mvc;

import com.entities.Account;
import com.rest.resources.AccountResources;
import com.rest.resources.BlogListResources;
import com.rest.resources.asm.AccountResourceAsm;
import com.rest.resources.asm.BlogListResourceAsm;
import com.services.AccountService;
import com.services.exceptions.AccountDoesNotExistsException;
import com.services.exceptions.AccountExistsException;
import com.services.exceptions.ConflictException;
import com.services.exceptions.NotFoundException;
import com.services.utilitiy.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/rest/accounts")
public class AccountController {
    private AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResources> creatAccount(@RequestBody AccountResources sentAccount) {
        try {
            Account createdAccount = service.creatAccount(sentAccount.toAccount());
            AccountResources resources = new AccountResourceAsm().toResource(createdAccount);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(URI.create(resources.getLink("self").getHref()));
            return new ResponseEntity<>(resources, httpHeaders, HttpStatus.CREATED);
        } catch (AccountExistsException exp) {
            throw new ConflictException(exp);
        }
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<AccountResources> getAccountById(@PathVariable Long accountId) {
        //DATABASE serarch
        Account acount = service.findAcount(accountId);

        //If not then return Not Found
        if (acount == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        //If account is there then we need processes it and return to UI (UI format)
        AccountResources accountResource = new AccountResourceAsm().toResource(acount);
        return new ResponseEntity<>(accountResource, OK);
    }

   /* @RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.POST)
    public ResponseEntity<BlogResources> createBlog(@PathVariable Long accountId, @RequestBody BlogEntryResources resources) {

        try {
            Blog createdBlog = service.createBlog(accountId, resources.toBlog());
            BlogResources blogResources = new BlogResourceAsm().toResource(createdBlog);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(URI.create(blogResources.getLink("self").getHref()));
            return new ResponseEntity<>(blogResources, httpHeaders, HttpStatus.CREATED);
        } catch (AccountDoesNotExistsException exception) {
            throw new BadRequestExceptions(exception);
        } catch (BlogExistsException blogEx) {
            throw new ConflictException(blogEx);
        }

    } */


    public Account getAccount(Long id) {
        return service.findAcount(id);
    }

    @RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.GET)
    public ResponseEntity<BlogListResources> findAllBlogs(@PathVariable Long accountId) {

        try {
            BlogList blogList = service.findBlogsByAccount(accountId);
            BlogListResources blogListResources = new BlogListResourceAsm().toResource(blogList);
            return new ResponseEntity<>(blogListResources, HttpStatus.OK);
        } catch (AccountDoesNotExistsException exception) {
            throw new NotFoundException(exception);
        }

    }
}
