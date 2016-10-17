package com.poppy.sport.quartz.job;

import java.util.List;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.poppy.sport.bean.User;
import com.poppy.sport.service.ScoreService;
import com.poppy.sport.service.UserService;

@IocBean
public class MyQuartzJob implements Job {
	private static final Log log = Logs.get();
	@Inject
	private UserService userService;
	
	@Inject
	private ScoreService scoreService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		List<User> users = userService.query();
		for (User user : users) {
			log.error(user.getName());
			scoreService.autoSave(user.getOpenid());
		}
		log.error("--------------------");
	}

}
