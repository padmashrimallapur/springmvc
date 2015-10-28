package com.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class AccountListResorce extends ResourceSupport {
    private List<AccountResources> accounts = new ArrayList<>();

    public List<AccountResources> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountResources> accounts) {
        this.accounts = accounts;
    }
}
