package com.bdc.dao;

import com.bdc.entity.ApiLtLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApiLtLogDao {
    Page<ApiLtLog> findAll(Pageable pageable);

    List<ApiLtLog> findAlls();
}
