package com.poppy.sport.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.poppy.sport.bean.Score;
import com.poppy.sport.bean.User;
import com.poppy.sport.service.ScoreService;
import com.poppy.sport.service.UserService;
import com.poppy.sport.vo.AjaxReault;
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

	@GET
	@Ok("beetl:score/chart.btl")
	@At("/chart")
	public void chart() {

	}

	// @POST
	// @At("/chart")
	// public List<Serie<Integer>> lineChart() {
	//
	// List<Serie<Integer>> result = new ArrayList<Serie<Integer>>();
	//
	// List<User> users = userService.queryUserScores();
	//
	// for (User user : users) {
	// Map<String, Integer> scores = new LinkedHashMap<String, Integer>();
	// initChartDate(scores);
	// for (Score score : user.getUserScores()) {
	// scores.put(new DateTime(score.getRankDate()).toString("yyyy-MM-dd"),
	// score.getRankScore());
	// }
	//
	// Serie<Integer> serie = new Serie<Integer>();
	// serie.setName(user.getName());
	// List<Integer> data = new ArrayList<Integer>();
	// for (Entry<String, Integer> sc : scores.entrySet()) {
	// data.add(sc.getValue());
	// }
	// serie.setData(data);
	// result.add(serie);
	//
	// }
	//
	// return result;
	//
	// }

	@At("/auto")
	public AjaxReault auto() {
		List<User> users = userService.query();
		List<Score> scores = new ArrayList<Score>();
		Random random = new Random();
		for (User user : users) {
			Score score = scoreService.autoSave(user.getOpenid());
			scores.add(score);
			log.info("---" + user.getName() + " : " + score.getRankScore());
			int sleep = random.nextInt(10) + 10;
			try {
				Thread.sleep(sleep);// 避免操作太快被服务器拒绝
			} catch (InterruptedException e) {
				log.error(e);
			}
		}
		log.info("--------------------");
		return AjaxReault.success("autosuccess", scores);
	}

	private void initDate(Map<Object, Object> score) {
		DateTime start = new DateTime(2016, 10, 1, 0, 0);
		DateTime now = new DateTime();
		int d = Days.daysBetween(start, now).getDays();
		for (int i = d; i >= 0; i--) {
			DateTime dateTime = start.plusDays(i);
			score.put(dateTime.toString("yyyy-MM-dd"), -1);
		}
	}

	private void initChartDate(Map<String, Integer> score) {
		DateTime start = new DateTime(2016, 10, 1, 0, 0);
		DateTime now = new DateTime();
		int d = Days.daysBetween(start, now).getDays();
		for (int i = 0; i <= d; i++) {
			DateTime dateTime = start.plusDays(i);
			score.put(dateTime.toString("yyyy-MM-dd"), -1);
		}
	}

	@POST
	@At("/list")
	public DataTableRecord<Map<Object, Object>> list(@Param("..") DataTableParam param, @Param("startDate") Date startDate, @Param("endDate") Date endDate) {
		log.info(param);
		log.info(new DateTime(startDate).toString("yyyy-MM-dd"));
		log.info(endDate);
		DataTableRecord<Map<Object, Object>> result = new DataTableRecord<Map<Object, Object>>();
		result.setDraw(param.getDraw());

		List<User> users = userService.queryUserScores(startDate, endDate);

		List<Map<Object, Object>> data = new ArrayList<Map<Object, Object>>();

		for (User user : users) {
			Map<Object, Object> scores = new HashMap<Object, Object>();
			scores.put("name", user.getName());
			initDate(scores);
			for (Score score : user.getUserScores()) {
				scores.put(new DateTime(score.getRankDate()).toString("yyyy-MM-dd"), score.getRankScore());
			}
			data.add(scores);
		}

		result.setRecordsFiltered(users.size());
		result.setRecordsTotal(users.size());

		result.setData(data);
		return result;
	}
}
