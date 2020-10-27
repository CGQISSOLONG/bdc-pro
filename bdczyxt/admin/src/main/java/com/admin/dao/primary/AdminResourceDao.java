package com.admin.dao.primary;

import com.admin.entity.primary.AdminResource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface AdminResourceDao {
    AdminResource findone(String code);

    List<AdminResource> findAll();

    void save(AdminResource adminResource);

    void modify(AdminResource resource);

    List<AdminResource> getEnableResources();
}
