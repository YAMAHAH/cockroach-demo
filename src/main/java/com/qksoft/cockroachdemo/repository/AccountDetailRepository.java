package com.qksoft.cockroachdemo.repository;

import com.qksoft.cockroachdemo.model.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailRepository extends JpaRepository<AccountDetail,Long> {
}
