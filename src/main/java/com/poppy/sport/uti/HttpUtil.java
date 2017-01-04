package com.poppy.sport.uti;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.nutz.json.Json;

import com.poppy.sport.bean.RankResult;
import com.poppy.sport.exception.RankException;

public class HttpUtil {
	private static CloseableHttpClient client;
	private static BasicCookieStore cookieStore;

	public static CloseableHttpClient getHttpClient() {
		if (client == null) {
			client = makeDefaultClient();
		}
		return client;
	}

	public static void depose() {
		if (client != null) {
			try {
				client.close();
			} catch (IOException e) {
			}
		}
		cookieStore = null;
		client = null;
	}

	public static String HWSTEPRANKSK = System.getProperty("HWSTEPRANKSK");
	public static String PASS_TICKET = System.getProperty("PASS_TICKET");
	private static String Q_UA2 = "QV=3&PL=ADR&PR=WX&PP=com.tencent.mm&PPVN=6.3.30&TBSVC=36842&CO=BK&COVC=036872&PB=GE&VE=GA&DE=PHONE&CHID=0&LCID=9422&MO= MINOTELTE &RL=1080*1920&OS=6.0.1&API=23";
	private static String Q_GUID = "187a6c3cf9b9a3dcac4d1ad613b788cb";
	private static String Q_AUTH = "31045b957cf33acf31e40be2f3e71c5217597676a9729f1b";

	public static HttpGet getHttpGet(String openid) {
		HttpGet httpGet = new HttpGet("https://hw.weixin.qq.com/steprank/step/personal?openid=" + openid + "&fromShare=1&from=singlemessage&isappinstalled=0&pass_ticket2=" + PASS_TICKET);
		httpGet.setHeader("accept-language", "zh-CN,zh;q=0.8,en-us;q=0.6,en;q=0.5;q=0.4");
		httpGet.setHeader("connection", "keep-alive");
		httpGet.setHeader("accept-encoding", "gzip, deflate");
		httpGet.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpGet.setHeader("user-agent", "Mozilla/5.0 (Linux; Android 6.0.1; MI NOTE LTE Build/MMB29M) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.8 TBS/036824 Safari/537.36 MicroMessenger/6.3.27.880 NetType/WIFI Language/zh_CN");
		httpGet.setHeader("Host", "hw.weixin.qq.com");
		httpGet.setHeader("Q-UA2", Q_UA2);
		httpGet.setHeader("Q-GUID", Q_GUID);
		httpGet.setHeader("Q-Auth", Q_AUTH);
		// httpGet.setHeader("Cookie",
		// "hwstepranksk=; pass_ticket=mc4oTSakN7sJr0V35NqW4vKPqsrKjAw%2B0tTqEJWXsHw%3D");
		return httpGet;
	}

	private static CloseableHttpClient makeDefaultClient() {

		HWSTEPRANKSK = System.getProperty("HWSTEPRANKSK");
		PASS_TICKET = System.getProperty("PASS_TICKET");

		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(200);
		cm.setDefaultMaxPerRoute(20);
		if (cookieStore == null) {
			cookieStore = new BasicCookieStore();
		}
		BasicClientCookie cookie = new BasicClientCookie("hwstepranksk", HWSTEPRANKSK);
		cookie.setDomain("hw.weixin.qq.com");
		cookieStore.addCookie(cookie);

		BasicClientCookie cookie2 = new BasicClientCookie("pass_ticket", PASS_TICKET);
		cookie2.setDomain("hw.weixin.qq.com");
		cookieStore.addCookie(cookie2);
		return HttpClients.custom().setConnectionManager(cm).setDefaultCookieStore(cookieStore).build();
	}

	public static String getHost(String url) {
		if (url == null || url.trim().equals("")) {
			return "";
		}
		String host = "";
		Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
		Matcher matcher = p.matcher(url);
		if (matcher.find()) {

			// host = matcher.group();
		}
		return host;
	}

	public static RankResult getRank(String openid) {
		String html = null;
		try {
			html = _getHtml(openid);
		} catch (ClientProtocolException e) {
			throw new RankException(e);
		} catch (IOException e) {
			throw new RankException(e);
		}
		if (html == null || "".equals(html)) {
			return null;
		}
		Matcher matcher = Pattern.compile("window.json\\s*=\\s*\\{.*\\};").matcher(html);
		String json = null;
		while (matcher.find()) {
			json = matcher.group();
		}
		RankResult rankResult = null;
		if (json != null) {
			json = json.replaceAll("window.json\\s*=\\s*", "").replaceAll(";", "");
			rankResult = Json.fromJson(RankResult.class, json);
			rankResult.setHtml(html);
		}
		return rankResult;
	}

	private static String _getHtml(String openid) throws ClientProtocolException, IOException {
		String html = null;
		CloseableHttpResponse response1 = getHttpClient().execute(getHttpGet(openid));
		HttpEntity entity1 = response1.getEntity();
		html = EntityUtils.toString(entity1);
		return html;
	}

	public static void main(String[] args) {
		try {
			CloseableHttpResponse response1 = getHttpClient().execute(getHttpGet("o95gOtxJYn3sb3Bw6Qj1iNescmWc"));
			HttpEntity entity1 = response1.getEntity();
			String html = EntityUtils.toString(entity1);
			System.out.println(html);
			// RankResult rankResult = getRank(html);
			// System.out.println(Json.toJson(rankResult));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
