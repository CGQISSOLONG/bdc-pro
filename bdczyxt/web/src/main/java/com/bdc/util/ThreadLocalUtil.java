package com.bdc.util;

import com.bdc.entity.Users;

public class ThreadLocalUtil {
    private ThreadLocal<Users> userThreadLocal = new ThreadLocal<>();


    private static ThreadLocalUtil instance = new ThreadLocalUtil();

    private ThreadLocalUtil() {
    }

    public static ThreadLocalUtil getInstance() {
        return instance;
    }

    /**
     * 将用户对象绑定到当前线程中，键为pubUserThreadLocal对象，值为pubUser对象
     *
     */
    public void bind(Users user) {
        userThreadLocal.set(user);
    }


    /**
     * 将用户数据绑定到当前线程中，键为pubUserThreadLocal对象，值为pubUser对象
     *
     */
    public void bind(String username) {
        Users user = new Users();
        user.setUsername(username);
        bind(user);
    }


    /**
     * 得到绑定的用户对象
     *
     * @return
     */
    public Users getPubUserInfo() {
        Users user = userThreadLocal.get();
        remove();
        return user;
    }


    /**
     * 移除绑定的用户对象
     */
    public void remove() {
        userThreadLocal.remove();
    }

}
