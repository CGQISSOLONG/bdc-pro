package com.bdc.dao;

import com.bdc.entity.primary.UserLoginLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoginLogDao {
    Page<UserLoginLog> findAll(Pageable pageable);

    List<UserLoginLog> findAlls();

    void save( UserLoginLog pubUserLoginLog);
}
