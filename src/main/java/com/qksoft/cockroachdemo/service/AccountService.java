package com.qksoft.cockroachdemo.service;

import com.qksoft.cockroachdemo.model.Account;
import com.qksoft.cockroachdemo.model.AccountItem;
import com.qksoft.cockroachdemo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AccountService {

    @Value("${spring.datasource.username}")
    private String userName;

    @Autowired
    private AccountRepository accountRepository;

    public void init() {

        Account account1 = new Account(4, 1500);
        Account account2 = new Account(5, 1600);
        AccountItem[] accountItems = {
                new AccountItem(5, 500,account1),
                new AccountItem(6, 600,account1),
        };
        account1.setAccountItems(Arrays.asList(accountItems));
        AccountItem[] accountItems2 = {
                new AccountItem(7, 100,account2),
                new AccountItem(8, 200,account2),
        };
        account2.setAccountItems(Arrays.asList(accountItems2));

        Account account3 = new Account(7, 1700);
        AccountItem[] accountItems3 = {
                new AccountItem(11, 500,account3),
                new AccountItem(12, 600,account3),
        };
        account3.getAccountItems().addAll(Arrays.asList(accountItems3));
        Account[] accounts = {account1, account2,account3 };


        accountRepository.saveAll(Arrays.asList(accounts));
    }

    public List<Account> searchAll(){
        return this.accountRepository.findAll();
    }
}
