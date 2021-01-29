package com.sports.domain;

public class CategoryPlus {
    /**
     * id
     */
    private int id;
    /**
     * 分类名
     */
    private String name;
    /**
     * 分类的创建者名
     */
    private String creator;
    private String activityNum;
    private String userNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getActivityNum() {
        return activityNum;
    }

    public void setActivityNum(String activityNum) {
        this.activityNum = activityNum;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    @Override
    public String toString() {
        return "CategoryPlus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", activityNum='" + activityNum + '\'' +
                ", userNum='" + userNum + '\'' +
                '}';
    }
}
