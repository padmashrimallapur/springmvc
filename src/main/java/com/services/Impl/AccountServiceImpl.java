package com.services.Impl;

import com.entities.Account;
import com.entities.Blog;
import com.entities.BlogEntryList;
import com.respositories.AccountRepo;
import com.respositories.BlogRepo;
import com.services.AccountService;
import com.services.exceptions.AccountDoesNotExistsException;
import com.services.exceptions.AccountExistsException;
import com.services.exceptions.BlogExistsException;
import com.services.exceptions.BlogNotFoundException;
import com.services.utilitiy.BlogList;
import com.utilities.AccountList;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl implements AccountService {


    private AccountRepo accountRepo;

    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private BlogEntryRepo entryRepo;


    @Override
    public Account findAcount(Long id) {
        return accountRepo.findAcount(id);
    }

    @Override
    public Account creatAccount(Account data) {
        Account account = accountRepo.findAccountByName(data.getName());
        if (account != null) {
            throw new AccountExistsException();
        }
        return accountRepo.creatAccount(data);
    }

    @Override
    public Blog createBlog(Long id, Blog data) {
        Blog sameBlogTitle = blogRepo.findBlogByTitle(data.getTitle());

        if (sameBlogTitle != null) {
            throw new BlogExistsException();
        }

        Account account = accountRepo.findAcount(id);
        if (account != null) {
            throw new AccountExistsException();
        }

        Blog blog = blogRepo.createBlog();
        blog.setOwner(account);

        return blog;
    }

    @Override
    public Account toBlog() {
        return null;
    }

    @Override
    public BlogList findBlogsByAccount(long l) {
        Account account = accountRepo.findAcount(l);
        if (account == null) {
            throw new AccountDoesNotExistsException();
        }
        return new BlogList(blogRepo.findBlogsByAccount(l));
    }

    @Override
    public BlogEntryList findAllBlogEntries(Long blogId) {
        Blog blog = blogRepo.findBlog(blogId);
        if (blog == null) {
            throw new BlogNotFoundException();
        }
        return new BlogEntryList(blogId, entryRepo.findByBlogId(blogId));

    }

    @Override
    public AccountList findAllAccounts() {
        return new AccountList(accountRepo.findAllAccounts());
    }

    @Override
    public Account findByAccountName(String name) {
        return accountRepo.findAccountByName(name);
    }
}
