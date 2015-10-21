package com.services;

import com.entities.Account;
import com.entities.Blog;

public interface AccountService {

    Account findAcount(Long id);

    Account creatAccount(Account data);

    Blog createBlog(Long id, Blog data);

    Account toBlog();
}
