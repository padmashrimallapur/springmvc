package com.rest.resources;

import com.core.models.entities.Account;
import org.springframework.hateoas.ResourceSupport;

public class AccountResources extends ResourceSupport {

    private String name;
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Account toAccount() {
        Account account = new Account();
        account.setName(this.getName());
        account.setPassword(this.getPassword());
        return account;
    }
}
