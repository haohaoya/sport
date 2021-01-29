package com.sports.domain;

/**
 * 活动的javabean实体类
 */
public class Activity {
    /**
     * id
     */
    private int id;
    /**
     * 活动名
     */
    private String name;
    /**
     * 活动信息
     */
    private String information;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 活动所属分类
     */
    private String category;
    /**
     * 创建时间
     */
    private String time;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", information='" + information + '\'' +
                ", creator='" + creator + '\'' +
                ", category='" + category + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
