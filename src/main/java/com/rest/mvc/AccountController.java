package com.rest.mvc;

import com.core.models.entities.Account;
import com.core.models.entities.Blog;
import com.core.services.AccountService;
import com.core.services.exceptions.AccountDoesNotExistsException;
import com.core.services.exceptions.AccountExistsException;
import com.core.services.exceptions.BlogExistsException;
import com.core.services.utilitiy.AccountList;
import com.core.services.utilitiy.BlogList;
import com.rest.exceptions.BadRequestExceptions;
import com.rest.exceptions.ConflictException;
import com.rest.exceptions.NotFoundException;
import com.rest.resources.*;
import com.rest.resources.asm.AccountListResourceAsm;
import com.rest.resources.asm.AccountResourceAsm;
import com.rest.resources.asm.BlogListResourceAsm;
import com.rest.resources.asm.BlogResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;

@Controller
@RequestMapping("/rest/accounts")
public class AccountController {

    private AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<AccountListResorce> findAllAcounts(@RequestParam(value = "name", required = false) String name) {
        AccountList list;
        if (name == null) {
            list = service.findAllAccounts();
        } else {
            Account account = service.findByAccountName(name);
            if (account == null) {
                list = new AccountList(new ArrayList<Account>());
            } else {
                list = new AccountList(Collections.singletonList(account));
            }
        }

        AccountListResorce resorce = new AccountListResourceAsm().toResource(list);
        return new ResponseEntity<>(resorce, HttpStatus.OK);
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


    @RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.POST)
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

    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<AccountResources> getAccount(@PathVariable Long accountId) {
        Account account = service.findAcount(accountId);

        if (account != null) {
            AccountResources resources = new AccountResourceAsm().toResource(account);
            return new ResponseEntity<>(resources, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
