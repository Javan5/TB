package com.TB.TB.repository;

import com.TB.TB.model.entity.currency.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CurrencyRepository extends JpaRepository<Currency, String>, JpaSpecificationExecutor<Currency> {
}
