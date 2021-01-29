package com.sports.service;

import com.sports.domain.User;

import java.util.List;

public interface UserAndUserService {
    /**
     * 添加好友
     * @param name 用户名
     * @param username 好友名
     * @return 成功：0   失败：1
     */
    int addFriend(String name, String username);

    /**
     * 获取好友列表
     * @param username 获取此用户的好友列表
     * @return
     */
    List<User> getFriend(String username);

    /**
     * 删除好友
     * @param name 操作者用户名
     * @param username 要删除好友用户名
     * @return
     */
    int deleteFriend(String name, String username);
}
