package com.qksoft.cockroachdemo.model;
import com.qksoft.cockroachdemo.model.AccountDetail;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
    @Entity
    @Table(name = "accounts"
    )
    public class Account {
        @Id
        @Column(name = "id")
        private long id;

        @Column(name = "balance")
        private long balance;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
        private List<AccountDetail> accountItems = new ArrayList<>();

        // Convenience constructor.
        public Account(int id, int balance) {
            this.setId(id);
            this.setBalance(balance);
        }

        public Account(int id, int balance, List<AccountDetail> items) {
            this.setId(id);
            this.setBalance(balance);
            this.setAccountItems(items);
        }

        // Hibernate needs a default (no-arg) constructor to create model objects.
        public Account() {
        }

        public List<AccountDetail> getAccountItems() {
            return accountItems;
        }

        public void setAccountItems(List<AccountDetail> accountItems) {
            this.accountItems = accountItems;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getBalance() {
            return balance;
        }

        public void setBalance(long balance) {
            this.balance = balance;
        }
    }
