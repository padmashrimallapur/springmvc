package com.core.respositories;

import com.core.models.entities.Account;

import java.util.List;

public interface AccountRepo {

    Account findAcount(Long id);

    Account creatAccount(Account data);

    Account findAccountByName(String name);

    List<Account> findAllAccounts();
}
