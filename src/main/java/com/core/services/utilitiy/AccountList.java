package com.core.services.utilitiy;

import com.core.models.entities.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountList {


    private List<Account> accounts = new ArrayList<>();

    public List<Account> getAccounts() {

        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public AccountList(List<Account> list) {
        this.accounts = list;
    }
}
