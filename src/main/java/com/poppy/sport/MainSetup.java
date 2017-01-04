package com.poppy.sport;

import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.integration.quartz.NutQuartzCronJobFactory;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.poppy.sport.bean.User;

public class MainSetup implements Setup {

	@Override
	public void init(NutConfig nc) {
		Ioc ioc = nc.getIoc();
		PropertiesProxy conf = ioc.get(PropertiesProxy.class,"conf");
		System.setProperty("PASS_TICKET", conf.get("PASS_TICKET"));
		System.setProperty("HWSTEPRANKSK", conf.get("HWSTEPRANKSK"));
		Dao dao = ioc.get(Dao.class);
		Daos.createTablesInPackage(dao, User.class, false);
		Daos.migration(dao, "com.poppy.sport.bean", true, false);
		// 获取NutQuartzCronJobFactory从而触发计划任务的初始化与启动
		ioc.get(NutQuartzCronJobFactory.class);
	}

	@Override
	public void destroy(NutConfig nc) {

	}

}
