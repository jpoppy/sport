package com.poppy.sport.module;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.PairAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.poppy.sport.bean.RankCookie;
import com.poppy.sport.service.RankCookieService;
import com.poppy.sport.vo.AjaxReault;

@IocBean
@At("/c")
public class RankCookieInfoModule {
	@Inject
	private RankCookieService rankCookieService;

	@AdaptBy(type = PairAdaptor.class)
	@At("/add")
	@POST
	public AjaxReault addUser(@Param("..") RankCookie rankCookie) {

		rankCookieService.insert(rankCookie);
		return AjaxReault.success("添加成功").setData(rankCookie);
	}
	
	@GET
	@At("/add")
	@Ok("beetl:user/addcookie.btl")
	public void addCookie() {

	}
}
