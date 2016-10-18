package com.poppy.sport.module;

import java.util.HashMap;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.adaptor.PairAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.poppy.sport.bean.User;
import com.poppy.sport.service.UserService;
import com.poppy.sport.uti.URLUtil;

@IocBean
@At("/user")
public class UserModule {

	@Inject
	private UserService userService;

	@At("/a")
	public int count() {
		return userService.count();
	}

	@AdaptBy(type = PairAdaptor.class)
	@At("/add")
	@POST
	public User addUser(@Param("..") User user, @Param("_url") String wxurl) {
		if (Strings.isNotBlank(wxurl)) {
			user.setOpenid(URLUtil.URLRequest(wxurl).get("openid"));
		}
		userService._fastInsert(user);
		return user;
	}

	@GET
	@At("/add")
	@Ok("beetl:user/addUser.btl")
	public void addUser() {

	}

	@At
	@Ok("beetl:hello.btl")
	public Map<String, String> beetl() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("name", "chenb-c");
		data.put("age", "27");
		return data;
	}
}
