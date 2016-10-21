package weixin;

import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.junit.Test;

public class Normal {
	@Test
	public void random() {
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			System.out.println(random.nextInt(40) + 10);
		}
	}
	@Test
	public void dateBetween(){
		DateTime start = new DateTime(2016,10,1, 0, 0);
		DateTime now = new DateTime();
		int d = Days.daysBetween(start, now).getDays();
		for (int i = d; i >=0; i--) {
			DateTime dateTime = start.plusDays(i);
			System.out.println(dateTime.toString("yyyy-MM-dd"));
		}
		System.out.println(d);
	}
}
