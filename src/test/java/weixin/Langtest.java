package weixin;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;
import org.nutz.lang.Lang;

public class Langtest {
	@Test
	public void test(){
		try {
			String json = Lang.readAll(new FileReader("F://gslq.js"));
			json = json.replaceAll("\"数量\"", "\"中标数量\"").replaceAll("\"含税单价\"", "\"中标含税单价\"").replaceAll("\"税率\"", "\"中标税率\"").replaceAll("\"税金\"", "\"中标税金\"").replaceAll("\"不含税单价\"", "\"中标不含税单价\"").replaceAll("\"运杂费\"", "\"中标运杂费\"").replaceAll("\"综合单价\"", "\"中标综合单价\"").replaceAll("\"综合总价\"", "\"中标综合总价\"");
			
			json = json.replaceAll("\"format\"\\s*:\\s*\",0.00\"", "\"format\":\"format\"");
			
			System.out.println(json);
			//Map o = (Map)Json.fromJson(json);
			
			//System.out.println(o.get("fields"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
