package com.sports.domain;

import net.sf.json.JSONObject;

import java.util.List;

/**
 * 活动的javabean实体类
 */
public class ActivityPlusPlus {
    private int id;
    private String name;
    private String information;
    private String creator;
    private String category;
    private String time;
    private String num;
    private List<User> user;
    private List<DiscussPlus> discuss;

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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<DiscussPlus> getDiscuss() {
        return discuss;
    }

    public void setDiscuss(List<DiscussPlus> discuss) {
        this.discuss = discuss;
    }

    @Override
    public String toString() {
        return "ActivityPlus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", information='" + information + '\'' +
                ", creator='" + creator + '\'' +
                ", category='" + category + '\'' +
                ", time='" + time + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
