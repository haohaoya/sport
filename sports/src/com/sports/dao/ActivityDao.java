package com.sports.dao;

import com.sports.domain.Activity;
import com.sports.domain.Category;
import com.sports.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 活动表的交互接口
 */
public interface ActivityDao {
    /**
     * 查询所有活动
     * @return 活动列表
     */
    @Select("select * from activity")
    List<Activity> selectAll();

    /**
     * 根据类别查询活动
     * @param category 类别名
     * @return 活动列表
     */
    @Select("select * from activity where category=#{0}")
    List<Activity> selectSomeByCategory(String category);

    /**
     * 根据名字模糊查询活动
     * @param name 名字
     * @return 活动列表
     */
    @Select("select * from activity where name like #{0}")
    List<Activity> selectSomeByName(String name);

    /**
     * 根据名字查询活动
     * @param name  名字
     * @return 活动 没有返回null
     */
    @Select("select * from activity where name=#{0}")
    Activity selectByName(String name);

    /**
     * 添加活动
     * @param activity 要添加的活动信息
     */
    @Insert("insert into activity values(null,#{name},#{information},#{creator},#{category},#{time})")
    void insertActivity(Activity activity);

    /**
     * 删除活动
     * @param name 活动名
     */
    @Delete("delete from activity where name=#{0}")
    void deleteActivityByName(String name);

    /**
     * 修改活动
     * @param activity 新活动信息
     */
    @Update("Update activity set information=#{information},category=#{category} where name=#{name}")
    void updateActivityByName(Activity activity);

    /**
     * 按照分类删除活动
     * @param category 分类名
     */
    @Delete("delete from activity where category=#{0}")
    void deleteActivityByCategory(String category);
}
