package com.TB.TB.repository;

import com.TB.TB.model.entity.news.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NewsRepository extends JpaRepository<News, String>, JpaSpecificationExecutor<News> {
}
