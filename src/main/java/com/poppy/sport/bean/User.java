package com.poppy.sport.bean;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_user")
public class User {
	@Id
	private int id;

	// 如果有一个字段声明了那么所有数据库字段都必须声明
	@Column
	private String name;

	@Column
	private String password;

	@Column("login_name")
	private String loginName;

	@Column("u_openid")
	@Comment("用户微信 openid")
	private String openid;

	@Column
	@ColDefine(width = 1)
	@Comment("用户状态，标识是否可以登录，0:不,1可以")
	private int state;

	@Column
	@ColDefine(width = 11)
	@Comment("手机号")
	private Long mobile;

	@Column
	@ColDefine(width = 1)
	@Comment("性别 1 男 0 女")
	private Integer sex;

	// @Many(target = Score.class, field = "openid")
	private List<Score> userScores;

	public void addScore(Score score) {
		if (userScores == null) {
			userScores = new ArrayList<Score>();
		}
		userScores.add(score);
	}

	public List<Score> getUserScores() {
		return userScores;
	}

	public void setUserScores(List<Score> userScores) {
		this.userScores = userScores;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
