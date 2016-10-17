package com.poppy.sport.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;

import com.poppy.sport.bean.User;

@IocBean(args = { "dao" })
public class UserService extends IdEntityService<User> {
	public UserService(Dao dao) {
		super(dao);
	}
}
