package weixin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.AnnotationIocLoader;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.json.Json;
import org.nutz.lang.Strings;

import com.poppy.sport.bean.RankResult;
import com.poppy.sport.bean.Score;
import com.poppy.sport.bean.User;
import com.poppy.sport.service.ScoreService;
import com.poppy.sport.service.UserService;
import com.poppy.sport.uti.HttpUtil;

public class Daotest {
	ComboIocLoader comboIocLoader = new ComboIocLoader(new JsonLoader("ioc/dao.js"), new AnnotationIocLoader("com.poppy.sport.service"));
	Ioc ioc = new NutIoc(comboIocLoader);
	Dao dao = ioc.get(Dao.class);
	ScoreService scoreService = ioc.get(ScoreService.class);
	UserService userService = ioc.get(UserService.class);
	String openid = "o95gOtwEig2YEX_gh40cAAqYfpqA";

	Map<String, String> userOpid = new HashMap<String, String>();

	@Before
	public void init() {
		// Daos.createTablesInPackage(dao, User.class, true);
		// Daos.migration(dao, "com.poppy.sport.bean", true, true);
	}

	@Test
	public void autoSave() {
		List<ExcelBean> data = PoiTest.testRead();
		List<String> notIn = new ArrayList<String>();
		for (ExcelBean excelBean : data) {
			String name = excelBean.getName();
			System.out.println("-->" + name + "::");
			// List<User> u = userService.query(Cnd.where("name", "=", name));
			// System.out.println(u.get(0).getName() + " : " +
			// u.get(0).getOpenid());
			List<User> u = userService.query(Cnd.where("name", "=", name.trim()));
			if (u.isEmpty()) {
				notIn.add(name);
				continue;
			}
			System.out.println(u.get(0).getName() + " : " + u.get(0).getOpenid());
			userOpid.put(u.get(0).getOpenid(), name);
			HashMap<Date, Integer> rankData = excelBean.getData();

			for (Entry<Date, Integer> ra : rankData.entrySet()) {
				Score score = new Score();

				score.setOpenid(u.get(0).getOpenid());
				score.setRankDate(ra.getKey());
				score.setRankScore(ra.getValue());
				saveScore(score);
			}

		}
	}

	private int _calculateScore(int rank) {
		if (rank >= 8000) {
			return 1;
		}
		return 0;
	}

	public Score saveScore(Score score) {
		String openid = score.getOpenid();
		if (Strings.isBlank(openid)) {
			return null;
		}

		Score old = _queryNowByopenid(openid, score.getRankDate());
		if (old == null) {
			score.setCreateTime(new Date());

			score.setRankDate(score.getRankDate());
			score.setUpdateTime(new Date());
			score.setHtml("excel导入");
			score.setDayScore(_calculateScore(score.getRankScore()));

			dao.fastInsert(score);
			return score;
		} else {

			old.setHtml("excel导入");
			old.setOpenid(score.getOpenid());
			old.setRankDate(score.getRankDate());

			old.setRankScore(score.getRankScore() > old.getRankScore() ? score.getRankScore() : old.getRankScore());

			old.setUpdateTime(new Date());

			old.setDayScore(_calculateScore(score.getRankScore()));

			dao.update(old);
			return old;
		}
	}

	private Score _queryNowByopenid(String openid, Date d) {
		DateTime nowDate = new DateTime(new DateTime(d).toString("yyyy-MM-dd"));
System.out.println(nowDate.toString("yyyy-MM-dd"));
		Score score = dao.fetch(Score.class, Cnd.where("openid", "=", openid).and("rankDate", "=", nowDate.toDate()));
		System.out.println(userOpid.get(openid));
		System.out.println(Json.toJson(score));
		return score;
	}

	// @Test
	// public void insert() {
	//
	// Score score = new Score();
	// score.setCreateTime(new Date());
	// score.setDayScore(10);
	// score.setHtml("XX");
	// score.setNickname("XSAA");
	// score.setOpenid(openid);
	// score.setRankDate(new Date());
	// score.setRankScore(688661);
	// score.setTitle("今日运动");
	// score.setUpdateTime(new Date());
	// dao.fastInsert(score);
	// // Score ssScore = dao.fetch(Score.class, 1);
	// DateTime dateTime = new DateTime(new DateTime().toString("yyyy-MM-dd"));
	// dateTime = new DateTime();
	// Score score2 = dao.fetch(Score.class, Cnd.where("openid", "=",
	// openid).and("rankDate", "=", dateTime.toDate()));
	// System.out.println(score2.getDayScore());
	// System.out.println(Json.toJson(score2));
	// // System.out.println(Json.toJson(score));
	// // Score s = dao.fetch(Score.class, 1);
	// // System.out.println(Json.toJson(s));
	// // UserService userService = ioc.get(UserService.class);
	// // System.out.println(userService);
	// // System.out.println(userService.count());
	// // System.out.println(userService._fastInsert(new User()));
	// // Sql sql = dao.sqls().create("user.my.t");
	// // sql.setCallback(Sqls.callback.entities());
	// // sql.setEntity(dao.getEntity(User.class));
	// // dao.execute(sql);
	// // List<User> users = sql.getList(User.class);
	// // System.out.println(Json.toJson(users));
	// }
	//
	// @Test
	// public void testInsertOpenid() {
	// Map<String, String> users = new HashMap<String, String>();
	// users.put("何兴毅",
	// "https://hw.weixin.qq.com/steprank/step/personal?openid=o95gOtwGxkHhWLDOm6ekbwrMtGIk&fromShare=1");
	// users.put("孟鹏彬",
	// "https://hw.weixin.qq.com/steprank/step/personal?openid=o95gOtwRDAbihtHcvg47THRLUVkc&fromShare=1");
	// users.put("张芳妹",
	// "https://hw.weixin.qq.com/steprank/step/personal?openid=o95gOt6XUWlOFCjP9r8X_FrHu-vM&fromShare=1");
	// users.put("朱天讯",
	// "https://hw.weixin.qq.com/steprank/step/personal?openid=o95gOt5r8sSQ9pxNSAPLojE6VDH4&fromShare=1");
	// users.put("孟勃婷",
	// "https://hw.weixin.qq.com/steprank/step/personal?openid=o95gOt3sRAmKRUEFkk6rAwl9NtFw&fromShare=1");
	// users.put("孙建利",
	// "https://hw.weixin.qq.com/steprank/step/personal?openid=o95gOtw_Y3xYjaytGVYlTryYKG8M&fromShare=1");
	// users.put("穆洪星",
	// "https://hw.weixin.qq.com/steprank/step/personal?openid=o95gOt-LCgTEYxKUF0Ju8aj6LG4Q&fromShare=1");
	// users.put("李文静",
	// "https://hw.weixin.qq.com/steprank/step/personal?openid=o95gOt7uxZO9JaHY_2Am0XkxVKnM&fromShare=1");
	// users.put("郝晓栋",
	// "https://hw.weixin.qq.com/steprank/step/personal?openid=o95gOt2L-b6zHe6qwfzUUBI5MRsQ&fromShare=1");
	// users.put("万成",
	// "https://hw.weixin.qq.com/steprank/step/personal?openid=o95gOt96el2UdvQ7tjYoR9wgM57w&fromShare=1");
	// users.put("封宁进",
	// "https://hw.weixin.qq.com/steprank/step/personal?openid=o95gOt7czativayg7MIoEJz3ZkJU&fromShare=1");
	// users.put("冀文静",
	// "https://hw.weixin.qq.com/steprank/step/personal?openid=o95gOt9-NblAJDN-jWochlbCfccw&fromShare=1");
	// users.put("朱建鹏",
	// "https://hw.weixin.qq.com/steprank/step/personal?openid=o95gOtzzGue5jZsmX8YTdlHz0HBw&fromShare=1");
	// users.put("蔺强",
	// "https://hw.weixin.qq.com/steprank/step/personal?openid=o95gOt7_6U43a12chCyRCtPr7oyo&fromShare=1");
	// List<User> data = new ArrayList<User>();
	// for (String name : users.keySet()) {
	// User user = new User();
	// user.setName(name);
	// user.setOpenid(getOpenid(users.get(name)));
	// data.add(user);
	//
	// }
	// dao.fastInsert(data);
	//
	// }
	//
	// @Test
	// public void featchLink() {
	// Sql sql = dao.sqls().create("user.score");
	// sql.setCallback(new SqlCallback() {
	//
	// @Override
	// public Object invoke(Connection conn, ResultSet rs, Sql sql) throws
	// SQLException {
	// List<User> list = new LinkedList<User>();
	//
	// Map<String, User> userMap = new HashMap<String, User>();
	//
	// while (rs.next()) {
	// String name = rs.getString("name");
	// Integer id = rs.getInt("id");
	// String openid = rs.getString("openid");
	// Date rankDate = rs.getDate("rank_date");
	// int dayScore = rs.getInt("day_score");
	// Date createTime = rs.getDate("create_time");
	// Date updateTime = rs.getDate("update_time");
	//
	// Score score = new Score();
	// score.setId(id);
	// score.setOpenid(openid);
	// score.setRankDate(rankDate);
	// score.setDayScore(dayScore);
	// score.setCreateTime(createTime);
	// score.setUpdateTime(updateTime);
	//
	// User user = userMap.get(name);
	// if (user == null) {
	// user = new User();
	// list.add(user);
	// }
	// user.setName(name);
	// user.addScore(score);
	// userMap.put(name, user);
	//
	// }
	//
	// return list;
	// }
	// });
	// dao.execute(sql);
	// List<User> list = sql.getList(User.class);
	// for (int i = 0; i < list.size(); i++) {
	// System.out.print(list.get(i).getName() + " : ");
	// for (Score s : list.get(i).getUserScores()) {
	// System.out.print("  " + s.getRankDate() + " - " + s.getRankScore() +
	// ";");
	// }
	// System.out.println();
	// }
	//
	// }
	//
	// private String getOpenid(String url) {
	// Map<String, String> data = CRequest.URLRequest(url);
	// return data.get("openid");
	//
	// }
}
