package com.poppy.sport.bean;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_cookieinfo")
public class RankCookie {
	@Id
	private int id;

	@Column
	@ColDefine(width = 1024)
	private String qua2;
	@Column
	private String qguid;
	@Column
	private String qauth;
	@Column
	private String hwstepranksk;
	@Column
	private String passTicket;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQguid() {
		return qguid;
	}

	public void setQguid(String qguid) {
		this.qguid = qguid;
	}

	public String getQua2() {
		return qua2;
	}

	public void setQua2(String qua2) {
		this.qua2 = qua2;
	}

	public String getQauth() {
		return qauth;
	}

	public void setQauth(String qauth) {
		this.qauth = qauth;
	}

	public String getHwstepranksk() {
		return hwstepranksk;
	}

	public void setHwstepranksk(String hwstepranksk) {
		this.hwstepranksk = hwstepranksk;
	}

	public String getPassTicket() {
		return passTicket;
	}

	public void setPassTicket(String passTicket) {
		this.passTicket = passTicket;
	}

}
