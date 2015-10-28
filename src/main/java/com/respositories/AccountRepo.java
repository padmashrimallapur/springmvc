package com.respositories;

import com.entities.Account;
import com.entities.Blog;

import java.util.List;

public interface AccountRepo {

    Account findAcount(Long id);

    Account creatAccount(Account data);

    Blog createBlog(Long id, Blog data);

    Account findAccountByName(String name);

    List<Account> findAllAccounts();
}
