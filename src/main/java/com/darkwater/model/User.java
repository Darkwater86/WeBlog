package com.darkwater.model;

/**
 * Created by Mr.Darkwater on 2017/7/21.
 */
public class User {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public User(){

    }
    public User(String name,
                String password,
                String salt,
                String headUrl){
        this.password = password;
        this.name = name;
        this.salt = salt;
        this.headUrl = headUrl;

    }

    private int id ;
    private String name;
    private String password;
    private String salt;
    private String headUrl;
}
