package com.poppy.sport.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.poppy.sport.bean.RankCookie;

@IocBean
public class RankCookieService {
	@Inject
	private Dao dao;

	public RankCookie insert(RankCookie rankCookie) {

		dao.clear(RankCookie.class);
		dao.insert(rankCookie);

		return rankCookie;
	}

	public RankCookie query() {
		return dao.query(RankCookie.class, null).get(0);
	}
}
