package com.poppy.sport.module;

import java.util.List;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.poppy.sport.bean.User;
import com.poppy.sport.service.ScoreService;
import com.poppy.sport.service.UserService;
import com.poppy.sport.vo.DataTableParam;
import com.poppy.sport.vo.DataTableRecord;

@IocBean
@At("/score")
public class ScoreModule {

	private static final Log log = Logs.get();

	@Inject
	private ScoreService scoreService;
	@Inject
	private UserService userService;

	@GET
	@Ok("beetl:score/list.btl")
	@At("/list")
	public void list() {

	}

	@POST
	@At("/list")
	public DataTableRecord<User> list(@Param("..") DataTableParam param) {
		log.info(param);
		DataTableRecord<User> result = new DataTableRecord<User>();
		result.setDraw(param.getDraw());
		
		List<User> users = userService.queryUserScores();
		
		result.setRecordsFiltered(users.size());
		result.setRecordsTotal(users.size());
		
		result.setData(users);
		return result;
	}
}
