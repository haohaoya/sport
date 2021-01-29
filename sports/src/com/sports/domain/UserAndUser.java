package com.sports.domain;

/**
 * 用户和用户关系的实体类
 */
public class UserAndUser {
    /**
     * id
     */
    private int id;
    /**
     * 用户一
     */
    private String userone;
    /**
     * 用户二
     */
    private String usertwo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserone() {
        return userone;
    }

    public void setUserone(String userone) {
        this.userone = userone;
    }

    public String getUsertwo() {
        return usertwo;
    }

    public void setUsertwo(String usertwo) {
        this.usertwo = usertwo;
    }

    @Override
    public String toString() {
        return "UserAndUser{" +
                "id=" + id +
                ", userone='" + userone + '\'' +
                ", usertwo='" + usertwo + '\'' +
                '}';
    }
}
