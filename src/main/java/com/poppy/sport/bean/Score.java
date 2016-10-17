package com.poppy.sport.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.json.JsonField;

@Table("t_score")
public class Score {
	@Id
	private int id;

	@Column
	@Comment("微信的openid")
	private String openid;

	@Column
	@Comment("微信运动获取到的nickname")
	private String nickname;

	@Column("rankdesc_title")
	@Comment("今日得分显示")
	private String title;

	@Column("rank_date")
	@ColDefine(type = ColType.DATE)
	@Comment("得分日期")
	@JsonField(dataFormat="yyyy-MM-dd")
	private Date rankDate;

	@Column("rankdesc_score")
	@Comment("今日步数")
	private int rankScore;

	@Column("day_score")
	@Comment("根据规则计算出来的每日得分")
	private int dayScore;

	@Column
	@ColDefine(type = ColType.TEXT)
	@Comment("获取到的微信原始html")
	private String html;

	@Column("create_time")
	@Comment("创建时间")
	private Date createTime;

	@Column("update_time")
	@Comment("更新时间")
	private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRankDate() {
		return rankDate;
	}

	public void setRankDate(Date rankDate) {
		this.rankDate = rankDate;
	}

	public int getRankScore() {
		return rankScore;
	}

	public void setRankScore(int rankScore) {
		this.rankScore = rankScore;
	}

	public int getDayScore() {
		return dayScore;
	}

	public void setDayScore(int dayScore) {
		this.dayScore = dayScore;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
