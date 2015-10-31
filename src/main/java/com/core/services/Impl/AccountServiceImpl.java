package com.core.services.Impl;

import com.core.models.entities.Account;
import com.core.models.entities.Blog;
import com.core.respositories.AccountRepo;
import com.core.respositories.BlogRepo;
import com.core.services.AccountService;
import com.core.services.exceptions.AccountDoesNotExistsException;
import com.core.services.exceptions.AccountExistsException;
import com.core.services.exceptions.BlogExistsException;
import com.core.services.utilitiy.AccountList;
import com.core.services.utilitiy.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private BlogRepo blogRepo;


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
        if (account == null) {
            throw new AccountExistsException();
        }

        Blog blog = blogRepo.createBlog(data);
        blog.setOwner(account);

        return blog;
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
    public AccountList findAllAccounts() {
        return new AccountList(accountRepo.findAllAccounts());
    }

    @Override
    public Account findByAccountName(String name) {
        return accountRepo.findAccountByName(name);
    }
}
