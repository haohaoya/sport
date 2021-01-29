package com.sports.domain;

/**
 * 评论的javabean实体类
 */
public class Discuss {
    /**
     * id
     */
    private int id;
    /**
     * 评论内容
     */
    private String information;
    /**
     * 创建者（评论发布用户名）
     */
    private String creator;
    /**
     * 评论所属活动
     */
    private String activity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Discuss{" +
                "id=" + id +
                ", information='" + information + '\'' +
                ", creator='" + creator + '\'' +
                ", activity='" + activity + '\'' +
                '}';
    }
}
