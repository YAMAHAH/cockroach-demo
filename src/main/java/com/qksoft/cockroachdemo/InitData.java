package com.qksoft.cockroachdemo;

import com.qksoft.cockroachdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {
    @Autowired
    AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        accountService.init();
    }
}
