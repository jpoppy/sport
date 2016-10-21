package com.poppy.sport.module;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

@IocBean
@At("/")
public class IndexModule {
	@Ok("->:/score/list")
	@At({"/index","/"})
	@Fail("void")
	public void index() {
		
	}
}
