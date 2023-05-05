package com.TB.TB.repository;

import com.TB.TB.model.entity.authorization.UserLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserLoanRepository extends JpaRepository<UserLoan, String>, JpaSpecificationExecutor<UserLoan> {
}
