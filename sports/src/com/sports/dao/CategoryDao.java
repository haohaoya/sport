package com.sports.dao;

import com.sports.domain.Category;
import com.sports.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 分类表的交互接口
 */
public interface CategoryDao {
    /**
     * 查询所有分类
     * @return 查询结果列表
     */
    @Select("select * from category")
    List<Category> selectAll();

    /**
     * 添加分类
     * @param category 分类信息
     */
    @Insert("insert into category values(null,#{name})")
    void insertCategory(Category category);

    /**
     * 根据名字查分类
     * @param name 活动名字
     * @return 查询到的分类信息
     */
    @Select("select * from category where name=#{0}")
    Category selectByName(String name);

    /**
     * 根据名字删除活动
     * @param name 活动名字
     */
    @Delete("delete from category where name=#{0}")
    void deleteCategoryByName(String name);
}
