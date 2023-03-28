package com.TB.TB.repository;

import com.TB.TB.model.entity.dbinfo.DbInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DbInfoRepository extends JpaRepository<DbInfo, String>, JpaSpecificationExecutor<DbInfo> {
}
