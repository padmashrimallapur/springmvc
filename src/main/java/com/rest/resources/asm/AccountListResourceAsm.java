package com.rest.resources.asm;

import com.core.services.utilitiy.AccountList;
import com.rest.mvc.AccountController;
import com.rest.resources.AccountListResorce;
import com.rest.resources.AccountResources;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList, AccountListResorce> {

    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResorce.class);
    }

    @Override
    public AccountListResorce toResource(AccountList accountList) {
        List<AccountResources> resourcesList = new AccountResourceAsm().toResources(accountList.getAccounts());
        AccountListResorce finalResource = new AccountListResorce();
        finalResource.setAccounts(resourcesList);
        return finalResource;
    }
}
