package com.qksoft.cockroachdemo.service;

import com.qksoft.cockroachdemo.model.Account;
import com.qksoft.cockroachdemo.model.AccountDetail;
import com.qksoft.cockroachdemo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void init() {

        Account account1 = new Account(2, 1500);
        Account account2 = new Account(3, 1600);
        AccountDetail[] accountItems = {
                new AccountDetail(1, 500,account1),
                new AccountDetail(2, 600,account1),
        };
        account1.setAccountItems(Arrays.asList(accountItems));
        AccountDetail[] accountItems2 = {
                new AccountDetail(3, 100,account2),
                new AccountDetail(4, 200,account2),
        };
        account2.setAccountItems(Arrays.asList(accountItems2));
        Account[] accounts = {account1, account2
                //  new Account(2, 1500, Arrays.asList(accountItems)),
                // new Account(3, 1600, Arrays.asList(accountItems2))
        };

        accountRepository.saveAll(Arrays.asList(accounts));
    }

    public List<Account> searchAll(){
        return this.accountRepository.findAll();
    }
}
