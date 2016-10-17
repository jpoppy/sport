package weixin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.nutz.json.Json;

import com.poppy.sport.bean.RankResult;

public class ScriptEn {
	public static void main(String[] args) {

		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("JavaScript");
		if (engine == null) {
			System.out.println("----------");
		}
		try {
			String html = getHtml();
			Matcher matcher = Pattern.compile("window.json\\s*=\\s*\\{.*\\};").matcher(html);
			String json = null;
			while (matcher.find()) {
				json = matcher.group();
			}
			if (json != null) {
				json = json.replaceAll("window.json\\s*=\\s*", "").replaceAll(";", "");
				RankResult obj = Json.fromJson(RankResult.class, json);
				System.out.println(obj.getRankdesc().getTitle() + " : " + obj.getRankdesc().getScore());
				System.out.println(Json.toJson(obj));
			}
			// Document document = Jsoup.parse(html);
			// Elements elements = document.select("script");
			// String script = "";
			// for (Element element : elements) {
			// if(element.html().contains("window.json")){
			// System.out.println(element.html());
			// script = element.html();
			// }
			// }
			engine.eval("{}");
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}

	private static String getHtml() {
		StringBuilder sb = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader("F://SS//test.html"));
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
