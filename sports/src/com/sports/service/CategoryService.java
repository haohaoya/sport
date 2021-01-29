package com.sports.service;

import com.sports.domain.Category;
import com.sports.domain.CategoryPlus;
import com.sports.domain.User;

import java.util.List;

public interface CategoryService {

    /**
     * 获取所有分类
     * @return 结果列表
     */
    List<CategoryPlus> getAllCategory();

    /**
     * 添加分类
     * @param name 分类名
     * @return 成功：0   失败：1
     */
    int addCategory(String name,String creator);

    /**
     * 删除分类
     * @param username 用户名
     * @param CategoryName 分类名
     * @return 成功：0   失败：1（没有权限）
     */
    int deleteCategory(String username, String CategoryName);

    /**
     * 根据分类获取用户列表
     * @param name
     * @return
     */
    List<User> getUserByCategory(String name);
}
