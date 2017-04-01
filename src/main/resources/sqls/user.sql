/* user.my.t */
select * from t_user

/* user.score */
SELECT
t_user.`name`,
t_score.id,
t_score.openid,
t_score.rank_date,
t_score.rankdesc_score,
t_score.day_score,
t_score.create_time,
t_score.update_time

FROM
t_user
LEFT JOIN t_score ON t_score.openid = t_user.u_openid 
$condition
ORDER BY `name`,rank_date ASC