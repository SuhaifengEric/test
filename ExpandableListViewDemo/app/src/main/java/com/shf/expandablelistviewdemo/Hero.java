package com.shf.expandablelistviewdemo;

/**
 * Created by lenovo苏 on 2016/10/7.
 * 项目 ：
 */

public class Hero {
    private String name;
    private int icon;

    public Hero(int icon,String name) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
