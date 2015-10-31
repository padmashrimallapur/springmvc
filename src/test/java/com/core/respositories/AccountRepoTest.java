package com.core.respositories;


import com.core.models.entities.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


//In order for the unit test to run a batch job, the framework must load the job's
//ApplicationContext @Runwith is used

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class AccountRepoTest {


    @Autowired
    private AccountRepo accountRepo;

    private Account account;

    @Before
    @Transactional
    @Rollback(false)
    public void setUp() {
        account = new Account();
        account.setName("name");
        account.setPassword("test");
        accountRepo.creatAccount(account);
    }

    @Test
    @Transactional
    public void findTest() {
        Account account = accountRepo.findAcount(this.account.getId());
        assertNotNull(account);
        assertEquals(account.getName(), "name");
        assertEquals(account.getPassword(), "test");
    }

}

