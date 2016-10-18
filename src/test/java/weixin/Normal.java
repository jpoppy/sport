package weixin;

import java.util.Random;

import org.junit.Test;

public class Normal {
	@Test
	public void random() {
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			System.out.println(random.nextInt(40) + 10);
		}
	}
}
