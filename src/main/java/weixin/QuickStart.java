package weixin;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

public class QuickStart {
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {

			CookieStore cookieStore = new BasicCookieStore();
			// Populate cookies if needed
			BasicClientCookie cookie = new BasicClientCookie("hwstepranksk", "WpT4V05Ulj02kkUrJ4BrxFkhIAhNiAd71U9eyt7QZMbFrUeB");
			cookie.setDomain("hw.weixin.qq.com");
			// cookieStore.addCookie(cookie);

			BasicClientCookie cookie2 = new BasicClientCookie("pass_ticket", "65Vk3UU%2BjMTj5Yvb2Ci%2BoGoMt9dAt3LQoVAWPWEpl1A%3D");
			// cookie2.setDomain("hw.weixin.qq.com");
			// cookieStore.addCookie(cookie2);
			// Set the store
			HttpGet httpGet = new HttpGet("https://hw.weixin.qq.com/steprank/oauth?returnurl=http%3A%2F%2Fhw.weixin.qq.com%2Fsteprank%2Fstep%2Fpersonal%3Fopenid%3Do95gOtxJYn3sb3Bw6Qj1iNescmWc%26fromShare%3D1%26from%3Dsinglemessage%26isappinstalled%3D0%26pass_ticket2%3D4DVaV1jWr8HaxB%252Bdw4UkOOa2l%252FjsIsXdVsPkti8NRak%253D&env=&code=0210TUqa1U1dPT1Quloa1NjTqa10TUqI&state=8XpKa-sxgXPlCBxMlzV0lw&pass_ticket=4DVaV1jWr8HaxB%2Bdw4UkOOa2l%2FjsIsXdVsPkti8NRak%3D");
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			// httpGet.setHeader("accept-language",
			// "zh-CN,zh;q=0.8,en-us;q=0.6,en;q=0.5;q=0.4");
			// httpGet.setHeader("connection", "keep-alive");
			// httpGet.setHeader("accept-encoding", "gzip, deflate");
			// httpGet.setHeader("accept",
			// "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			// httpGet.setHeader("user-agent",
			// "Mozilla/5.0 (Linux; Android 6.0.1; MI NOTE LTE Build/MMB29M) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.8 TBS/036824 Safari/537.36 MicroMessenger/6.3.27.880 NetType/WIFI Language/zh_CN");
			// httpGet.setHeader("Host", "hw.weixin.qq.com");
			// httpGet.setHeader("Q-UA2",
			// "QV=3&PL=ADR&PR=WX&PP=com.tencent.mm&PPVN=6.3.27&TBSVC=36803&CO=BK&COVC=036824&PB=GE&VE=GA&DE=PHONE&CHID=0&LCID=9422&MO= MINOTELTE &RL=1080*1920&OS=6.0.1&API=23");
			// httpGet.setHeader("Q-GUID", "187a6c3cf9b9a3dcac4d1ad613b788cb");
			// httpGet.setHeader("Q-Auth",
			// "31045b957cf33acf31e40be2f3e71c5217597676a9729f1b");
			// httpGet.setHeader("Cookie",
			// "hwstepranksk=WpT4V05Ulj02kkUrJ4BrxFkhIAhNiAd71U9eyt7QZMbFrUeB; pass_ticket=65Vk3UU%2BjMTj5Yvb2Ci%2BoGoMt9dAt3LQoVAWPWEpl1A%3D");
			try {
				System.out.println(response1.getStatusLine());
				HttpEntity entity1 = response1.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				System.out.println(EntityUtils.toString(entity1));
			} finally {
				response1.close();
			}

		} finally {
			// httpclient.close();
		}
	}
}
