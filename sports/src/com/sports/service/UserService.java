package com.sports.service;

import com.sports.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 注册账号
     * @param name
     * @param pwd
     * @param sex
     * @return 0：成功 1：用户名重复
     */
    int register(String name,String pwd,String sex);

    /**
     * 登录账号
     * @param name
     * @param pwd
     * @return 成功返回用户对象 失败返回null
     */
    User login(String name,String pwd);

    /**
     * 获取所有用户
     * @return 所有用户列表
     */
    List<User> gerAllUser();

    /**
     * 根据用户名字获取一个用户信息
     * @param name 用户名字
     * @return 用户信息
     */
    User selectOne(String name);

    /**
     * 根据用户名字模糊获取用户信息列表
     * @param name 用户名字
     * @return 获取到的用户信息列表
     */
    List<User> selectSome(String name);

    /**
     * 使用用户名修改用户数据
     * @param name 用户名
     * @param pwd 需要修改的新密码
     * @param sex 需要修改的新性别
     */
    void updateByUsername(String name,String pwd,String sex);

    /**
     * 使用手机号注册账号
     * @param name 用户名
     * @param phone 手机号
     * @param pwd 密码
     * @param sex 性别
     * @return 成功：0   用户名重复：1   手机号重复：2
     */
    int registerByPhone(String name, String phone, String pwd, String sex);

    /**
     * 成功返回对象，失败返回null
     * @param phone
     * @param pwd
     * @return
     */
    User loginByPhone(String phone, String pwd);

    /**
     * 修改用户数据
     * @param id 要修改的用户id
     * @param newusername 新用户名
     * @param newpassword 新密码
     * @param newphone 新手机号
     * @return 成功：0   用户名重复：1   手机号重复：2
     */
    int update(int id, String oldusername,String newusername, String newpassword, String newphone);
}
