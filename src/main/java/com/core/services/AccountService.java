package com.core.services;

import com.core.models.entities.Account;
import com.core.models.entities.Blog;
import com.core.services.utilitiy.AccountList;
import com.core.services.utilitiy.BlogList;

public interface AccountService {

    Account findAcount(Long id);
    Account creatAccount(Account data);
    Blog createBlog(Long id, Blog data);
    BlogList findBlogsByAccount(long l);
    AccountList findAllAccounts();
    Account findByAccountName(String name);
}
