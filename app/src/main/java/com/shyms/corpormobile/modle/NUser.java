package com.shyms.corpormobile.modle;

import java.io.Serializable;

/**
 * Created by Weimin on 4/8/2016.
 */
public class NUser implements Serializable {
    public String name; //用户姓名、昵称
    public String lastLoginTime;//最后登录时间
    public String ban;//权限
    public int points;//积分
    public String userId;//用户ID
    public String username;//用户名
    public String id_card;//身份证号码
    public String badges;//头衔
    public String log_verify_code;//登录用户识别码  string，12小时内有效
    public String avatar;
    public String avatarUrl;
    public String email;

    public NUser() {
        this.name = null;
        this.lastLoginTime = null;
        this.ban = null;
        this.points = 0;
        this.userId = null;
        this.username = null;
        this.id_card = null;
        this.badges = null;
        this.log_verify_code = null;
        avatar = null;
        avatarUrl = null;
        email = null;

    }

    public  void init(NUser user) {

        this.name = user.name;
        this.lastLoginTime = user.lastLoginTime;
        this.ban = user.ban;
        this.points = user.points;
        this.userId = user.userId;
        this.username = user.username;
        this.id_card = user.id_card;
        this.badges = user.badges;
        this.log_verify_code = user.log_verify_code;
        avatar = user.avatar;
        avatarUrl = user.avatarUrl;
        email = user.email;

    }


    public String getName() {
        return name;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public String getBan() {
        return ban;
    }

    public int getPoints() {
        return points;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getId_card() {
        return id_card;
    }

    public String getBadges() {
        return badges;
    }

    public String getLog_verify_code() {
        return log_verify_code;
    }


}
