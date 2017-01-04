package com.poppy.sport.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.poppy.sport.bean.RankCookie;
import com.poppy.sport.uti.HttpUtil;

@IocBean
public class RankCookieService {
	@Inject
	private Dao dao;

	public RankCookie insert(RankCookie rankCookie) {
		System.setProperty("PASS_TICKET", rankCookie.getPassTicket());
		System.setProperty("HWSTEPRANKSK", rankCookie.getHwstepranksk());
		HttpUtil.depose();
		return rankCookie;
	}

	public RankCookie query() {
		return dao.query(RankCookie.class, null).get(0);
	}
}
