package com.TB.TB.repository;

import com.TB.TB.model.entity.mortgage.Mortgage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MortgageRepository extends JpaRepository<Mortgage, String>, JpaSpecificationExecutor<Mortgage> {
}
