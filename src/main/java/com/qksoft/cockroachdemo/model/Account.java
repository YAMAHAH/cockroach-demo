package com.qksoft.cockroachdemo.model;

import com.qksoft.cockroachdemo.model.Listener.AccountPersistListener;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

    @Entity()
    @DynamicInsert
    @DynamicUpdate
    @Table(name = "accounts"
           // ,uniqueConstraints = {@UniqueConstraint(columnNames="id")}
            )
    @EntityListeners(AccountPersistListener.class)
//    @GeneratedValue(generator = "snowFlakeId")
    @GenericGenerator(name = "snowFlakeId", strategy = "com.qksoft.cockroachdemo.Sequence.SnowflakeId")
    public class Account {
        @Id
       // @GeneratedValue(generator = "snowFlakeId")
        @Column(name = "id",unique = true, nullable=false)
        private Long id;

        @Column(name = "balance" )
        private Long balance = null;

        @OneToMany( cascade = CascadeType.ALL ,mappedBy = "account",orphanRemoval = true)
        private List<AccountItem> accountItems = new ArrayList<>();

        // Convenience constructor.
        public Account(Long id, Long balance) {
            this.setId(id);
            this.setBalance(balance);
        }

        public Account(Long id, Long balance, List<AccountItem> items) {
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

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getBalance() {
            return balance;
        }

        public void setBalance(Long balance) {
            this.balance = balance;
        }
    }
