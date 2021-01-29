package com.sports.dao;

import com.sports.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 用户表的交互接口
 */
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param name 用户名
     * @return 用户信息
     */
    @Select("select * from user where username=#{0}")
    User selectByName(String name);

    /**
     * 根据手机号查询用户信息
     * @param phone 手机号
     * @return 用户信息
     */
    @Select("select * from user where phone=#{0}")
    User selectByPhone(String phone);

    /**
     * 添加用户
     * @param user 要添加的用户信息
     */
    @Insert("insert into user values(null,#{username},#{password},#{sex},null)")
    void insertUser(User user);

    /**
     * 查询全部用户
     * @return 查询到的用户列表
     */
    @Select("select * from user")
    List<User> selectAll();

    /**
     * 根据名字模糊查询用户列表
     * @param name 用户名字
     * @return 查询到的用户列表
     */
    @Select("select * from user where username like #{0}")
    List<User> selectSomeByName(String name);

    /**
     * 根据用户名修改用户信息
     * @param user 用户信息（包含要修改的用户的用户名，，以及新信息）
     */
    @Update("Update user set password=#{password},sex=#{sex} where username=#{username}")
    void updateByUsername(User user);

    /**
     * 添加新用户，，使用手机号的
     * @param user 要添加的用户信息
     */
    @Insert("insert into user values(null,#{username},#{password},#{sex},#{phone})")
    void insertUserByPhone(User user);

    /**
     * 为手机号写新接口，需要。。根据用户id修改用户信息
     * @param user 用户信息（包含要修改的用户的用户id，，以及新信息）
     */
    @Update("Update user set password=#{password},username=#{username},phone=#{phone} where id=#{id}")
    void updateByPhone(User user);
}
