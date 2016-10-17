package com.poppy.sport.bean;

import java.util.List;

public class RankResult {
	private RankDesc rankdesc;
	private List<RankDesc> rankdetaillist;
	private String openid;
	private String nickname;
	private String html;
	

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public RankDesc getRankdesc() {
		return rankdesc;
	}

	public void setRankdesc(RankDesc rankdesc) {
		this.rankdesc = rankdesc;
	}

	public List<RankDesc> getRankdetaillist() {
		return rankdetaillist;
	}

	public void setRankdetaillist(List<RankDesc> rankdetaillist) {
		this.rankdetaillist = rankdetaillist;
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

}

