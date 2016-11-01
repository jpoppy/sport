package com.poppy.sport.quartz.job;

import java.util.List;
import java.util.Random;

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
		Random random = new Random();
		for (User user : users) {
			scoreService.autoSave(user.getOpenid());
			int sleep = random.nextInt(40) + 10;
			try {
				Thread.sleep(sleep*500);//避免操作太快被服务器拒绝
			} catch (InterruptedException e) {
				log.error(e);
			}
		}
		log.info("--------------------");
	}

}
