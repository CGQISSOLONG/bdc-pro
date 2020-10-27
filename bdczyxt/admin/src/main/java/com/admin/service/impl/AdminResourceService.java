package com.admin.service.impl;



import com.admin.dao.primary.AdminResourceDao;
import com.admin.entity.primary.AdminResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "resource")
public class AdminResourceService {

    @Autowired
    private AdminResourceDao adminResourceDao;


    @Caching(
            put = @CachePut(key = "#resource.id"),
            evict = @CacheEvict(key = "'list'")
    )
    public AdminResource create(AdminResource resource) {
        resource.setId(UUID.randomUUID().toString());
        adminResourceDao.save(resource);
        return resource;
    }

    @Caching(
            put = @CachePut(key = "#resource.id"),
            evict = @CacheEvict(key = "'list'")
    )
    public AdminResource modify(AdminResource resource) {
//        adminResourceDao.save(resource);
        adminResourceDao.modify(resource);
        return resource;
    }

    @Cacheable
    public AdminResource get(String code){
        return adminResourceDao.findone(code);
    }

    @Cacheable(key = "'list'")
    public List<AdminResource> list(){
        return adminResourceDao.findAll();
    }

    @Caching(
            evict = {@CacheEvict(key = "#code"), @CacheEvict(key = "'list'")}
    )
    public void delete(String code){
        //roleRepository.removeRoleResourceByResourceId(code);
        //resourceRepository.remove(code);
    }

    @Caching(
            evict = {@CacheEvict(key = "#code"), @CacheEvict(key = "'list'")}
    )
    public void switchStatus(String code,boolean disable){
        //resourceRepository.switchStatus(code,disable);
    }

}
