package com.sports.service;

import com.sports.domain.Discuss;

import java.util.List;

public interface DiscussService {
    /**
     * 添加评论
     * @param username 操作用户名
     * @param information 评论内容
     * @param activity 活动名
     * @return
     */
    int addDiscuss(String username, String information, String activity);


    /**
     * 根据活动名查询评论集合
     * @param name 活动名
     * @return 查询结果
     */
    List<Discuss> getDiscuss(String name);
}
