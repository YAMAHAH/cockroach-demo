package com.qksoft.cockroachdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "accountItems",
indexes = { @Index(name = "id",columnList = "id",unique = true)}
)
public class AccountDetail {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "balance")
    private long balance;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    public AccountDetail(int id, int balance) {
        this.setId(id);
        this.setBalance(balance);

    }

    public AccountDetail(int id, int balance,Account account) {
        this.setId(id);
        this.setBalance(balance);
        this.setAccount(account);
    }

    public AccountDetail() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
