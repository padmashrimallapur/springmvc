package com.rest.resources.asm;

import com.entities.Account;
import com.mvc.AccountController;
import com.rest.resources.AccountResources;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResources> {

    public AccountResourceAsm() {
        super(AccountController.class, AccountResources.class);
    }

    @Override
    public AccountResources toResource(Account account) {
        AccountResources resources = new AccountResources();
        resources.setName(account.getName());
        resources.setPassword(account.getPassword());
        //TODO didn't understand need to discuss
        resources.add(linkTo(AccountController.class).slash(account.getId()).withSelfRel());
        return resources;
    }


}
