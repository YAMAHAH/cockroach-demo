package com.qksoft.cockroachdemo.model.Listener;

import com.qksoft.cockroachdemo.model.Account;

import javax.persistence.*;

public class AccountPersistListener {

    @PrePersist
    public void prePersist(Object entity) {
        if (entity.getClass() == Account.class) {
            System.out.println("--Before Persisting--");
            Account account = (Account) entity;
            //简单加密
            account.setBalance(reverse(account.getBalance()));
        }
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        if (entity.getClass() == Account.class) {
            System.out.println("--Before Updating--");
            Account account = (Account) entity;
            //简单加密
            account.setBalance(reverse(account.getBalance()));
        }
    }

    @PostUpdate
    public void postUpdate(Object entity) {
        System.out.println("--Post Updating--");
    }

    @PostLoad
    public void postLoad(Object entity) {
        if (entity.getClass() == Account.class) {
            System.out.println("--Post Loading--");
            Account account = (Account) entity;
            //简单加密
            account.setBalance(reverse(account.getBalance()));
        }
    }

    private long reverse(long raw) {
        //   StringBuffer sb = new StringBuffer(raw);
        return raw + 1;  //sb.reverse().toString();
    }
}
