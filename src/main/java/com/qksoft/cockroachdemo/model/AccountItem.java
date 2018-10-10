package com.qksoft.cockroachdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;

@Entity
@Table(name="accountItems"
       // u,niqueConstraints = {@UniqueConstraint(columnNames="id")}
    // ,indexes = { @Index(name = "account_id_idx",columnList = "account_id",unique = false)}
)
public class AccountItem {
    @Id
    @Column(name = "id", nullable=false, unique=true)
    private long id;

    @Column(name = "balance")
    private long balance;

//    @Column(insertable=false, updatable=false)
//    private long account_id;
   // foreignKey=@ForeignKey(foreignKeyDefinition="FOREIGN KEY (MANAGER_ID) REFERENCES MANAGER")
    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="account_id")
    @MapKeyColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    public AccountItem(int id, int balance) {
        this.setId(id);
        this.setBalance(balance);

    }

    public AccountItem(int id, int balance, Account account) {
        this.setId(id);
        this.setBalance(balance);
        this.setAccount(account);
    }

    public AccountItem() {
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

//    public long getAccount_id() {
//        return account_id;
//    }
//
//    public void setAccount_id(long account_id) {
//        this.account_id = account_id;
//    }
}
