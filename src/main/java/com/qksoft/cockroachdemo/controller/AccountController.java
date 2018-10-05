package com.qksoft.cockroachdemo.controller;

import com.qksoft.cockroachdemo.model.Account;
import com.qksoft.cockroachdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/account")
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    public void initData(){
        accountService.init();
    }


    @GetMapping("searchAll")
    public List<Account> searchAll(){
        return this.accountService.searchAll();
    }

}
