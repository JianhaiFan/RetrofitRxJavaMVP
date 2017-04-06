package com.xiaofan.retrofitrxjavamvp.entity.resulte;

/**
 * @author: 范建海
 * @createTime: 2017/3/30 11:15
 * @className:  SubjectResulte
 * @description: 测试显示数据
 * @changed by:
 */
public class SubjectResulte {
    private int id;
    private String name;
    private String title;

    @Override
    public String toString() {
        return "name->"+name+"\n";

    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
