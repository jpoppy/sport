package com.poppy.sport.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;

import com.poppy.sport.bean.Score;
import com.poppy.sport.bean.User;

@IocBean(args = { "dao" })
public class UserService extends IdEntityService<User> {
	public UserService(Dao dao) {
		super(dao);
	}

	public List<User> queryUserScores() {
		Sql sql = dao().sqls().create("user.score");
		sql.setCallback(new SqlCallback() {

			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<User> list = new LinkedList<User>();

				Map<String, User> userMap = new HashMap<String, User>();

				while (rs.next()) {
					String name = rs.getString("name");
					Integer id = rs.getInt("id");
					String openid = rs.getString("openid");
					Date rankDate = rs.getDate("rank_date");
					int dayScore = rs.getInt("day_score");
					Date createTime = rs.getDate("create_time");
					Date updateTime = rs.getDate("update_time");

					Score score = new Score();
					score.setId(id);
					score.setOpenid(openid);
					score.setRankDate(rankDate);
					score.setDayScore(dayScore);
					score.setCreateTime(createTime);
					score.setUpdateTime(updateTime);

					User user = userMap.get(name);
					if (user == null) {
						user = new User();
						list.add(user);
					}
					user.setName(name);
					user.addScore(score);
					userMap.put(name, user);

				}

				return list;
			}
		});
		dao().execute(sql);
		List<User> list = sql.getList(User.class);

		return list;
	}
}
