package com.services;

import com.entities.Account;
import com.entities.Blog;
import com.entities.BlogEntryList;
import com.services.utilitiy.BlogList;
import com.utilities.AccountList;

public interface AccountService {

    Account findAcount(Long id);

    Account creatAccount(Account data);

    Blog createBlog(Long id, Blog data);

    Account toBlog();

    BlogList findBlogsByAccount(long l);

    BlogEntryList findAllBlogEntries(Long accountId);

    AccountList findAllAccounts();

    Account findByAccountName(String name);
}
