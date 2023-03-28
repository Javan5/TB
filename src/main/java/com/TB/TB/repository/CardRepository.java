package com.TB.TB.repository;

import com.TB.TB.model.entity.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardRepository extends JpaRepository<Card, String>, JpaSpecificationExecutor<Card> {
}
