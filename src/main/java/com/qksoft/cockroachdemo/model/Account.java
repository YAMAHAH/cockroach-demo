package com.qksoft.cockroachdemo.model;

import com.qksoft.cockroachdemo.model.Listener.AccountPersistListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

    @Entity
    @Table(name = "accounts"
           // ,uniqueConstraints = {@UniqueConstraint(columnNames="id")}
            )
    @EntityListeners(AccountPersistListener.class)
    public class Account {
        @Id
        @Column(name = "id",unique = true, nullable=false)
        private long id;

        @Column(name = "balance")
        private long balance;

        @OneToMany( cascade = CascadeType.ALL ,mappedBy = "account")
        private List<AccountItem> accountItems = new ArrayList<>();

        // Convenience constructor.
        public Account(int id, int balance) {
            this.setId(id);
            this.setBalance(balance);
        }

        public Account(int id, int balance, List<AccountItem> items) {
            this.setId(id);
            this.setBalance(balance);
            this.setAccountItems(items);
        }

        // Hibernate needs a default (no-arg) constructor to create model objects.
        public Account() {
        }

        public List<AccountItem> getAccountItems() {
            return accountItems;
        }

        public void setAccountItems(List<AccountItem> accountItems) {
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
