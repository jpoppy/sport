package com.poppy.sport.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;

import com.poppy.sport.bean.RankResult;
import com.poppy.sport.bean.Score;
import com.poppy.sport.uti.HttpUtil;

@IocBean(args = { "dao" })
public class ScoreService extends IdEntityService<Score> {
	public ScoreService(Dao dao) {
		super(dao);
	}

	public Score saveScore(Score score) {
		String openid = score.getOpenid();
		if (StringUtils.isBlank(openid)) {
			return null;
		}

		Score old = _queryNowByopenid(openid);
		if (old == null) {
			score.setCreateTime(new Date());
			RankResult rankResult = HttpUtil.getRank(openid);
			if (rankResult == null) {
				return null;
			}
			_initScore(score, rankResult);
			dao().fastInsert(score);
			return score;
		} else {

			old.setHtml("手动录入");
			old.setOpenid(score.getOpenid());
			old.setRankDate(score.getRankDate());
			old.setRankScore(score.getRankScore());
			old.setUpdateTime(new Date());

			old.setDayScore(_calculateScore(score.getRankScore()));

			dao().update(old);
			return old;
		}
	}

	public Score autoSave(String openid) {

		RankResult rankResult = HttpUtil.getRank(openid);
		if (rankResult == null) {
			return null;
			//throw new RuntimeException("");
		}

		Score score = _queryNowByopenid(openid);

		if (score == null) {
			score = new Score();
			score.setCreateTime(new Date());
			_initScore(score, rankResult);
			dao().fastInsert(score);
		} else {
			_initScore(score, rankResult);
			dao().update(score);
		}

		return score;
	}

	private int _calculateScore(int rank) {
		if (rank >= 8000) {
			return 1;
		}
		return 0;
	}

	private Score _initScore(Score score, RankResult rankResult) {
		score.setRankDate(new Date());
		score.setUpdateTime(new Date());
		score.setHtml(rankResult.getHtml());
		score.setNickname(rankResult.getNickname());
		score.setOpenid(rankResult.getOpenid());
		score.setRankScore(rankResult.getRankdesc().getScore());
		score.setTitle(rankResult.getRankdesc().getTitle());
		score.setDayScore(_calculateScore(rankResult.getRankdesc().getScore()));
		return score;
	}

	private Score _queryNowByopenid(String openid) {
		DateTime nowDate = new DateTime(new DateTime().toString("yyyy-MM-dd"));
		Score score = dao().fetch(Score.class, Cnd.where("openid", "=", openid).and("rankDate", "=", nowDate.toDate()));
		return score;
	}
}
