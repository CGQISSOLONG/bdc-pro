package com.bdc.dao;

import com.bdc.common.base.CrudDao;
import com.bdc.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface UserDao extends CrudDao<Users> {

    Users findByName(String account);

    List<Users> findAll();

    Users findById(String id);

    void save(Users user);

   // List<Role> findotherRoles(String id);

    Users getPubUserByToken(String token);

    Integer insertSelective(Users pubUser);

    Integer findPubUserByCreateTime();

    List<Users> findPubUserList(Users pubUser);


    void updatePasswordById(@Param("password") String password, @Param("id") Integer id);

    void updatePasswordByAccount(@Param("password") String password, @Param("account") String account);

    void updateAccountById(@Param("id") Integer id, @Param("newAccount") String originAccount);

    /**
     * 根据账号查询用户
     *
     * @param account
     * @return
     */
    Users getPubUserByAccount(String account);


    /**
     * 根据账号查找用户
     *
     * @param account
     * @return
     */
    Users findPubUserByAccount(String account);

    /**
     * 切换状态
     *
     * @param id
     * @param status
     */
    @Transactional(rollbackFor = Exception.class)
    void updateStatusById(@Param("id") Integer id, @Param("status") Integer status);

    @Transactional(rollbackFor = Exception.class)
    void updatePubUserById(@Param("id") Integer pubUserId, @Param("account") String account, @Param("password") String password);

    /**
     * 据token获取用户
     *
     * @param token
     * @return
     */
    Users findByToken(String token);

    Users findOne(Integer id);


   // void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}