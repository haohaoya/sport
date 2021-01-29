package com.sports.domain;

/**
 * 用户和活动关系的javabean实体类
 */
public class UserAndActivity {
    /**
     * id
     */
    private int id;
    /**
     * 用户名
     */
    private String user;
    /**
     * 活动名
     */
    private String activity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "UserAndActivity{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", activity='" + activity + '\'' +
                '}';
    }
}
