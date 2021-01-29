package com.sports.domain;

/**
 * 分类的javabean实体类
 */
public class Category {
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                '}';
    }
}
