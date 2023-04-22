package com.TB.TB.repository;

import com.TB.TB.model.entity.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LoanRepository extends JpaRepository<Loan, String>, JpaSpecificationExecutor<Loan> {
}
