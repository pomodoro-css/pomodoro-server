package ch.css.pomodoro.service.statistic;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import ch.css.pomodoro.service.dto.User;
import ch.css.pomodoro.service.dto.UserStatistic;

public class StatisticManagerTest {

	@Test
	public void testGetBiggestTomatoes_sortLessThan5() {
		addUser("USER1", 33);
		addUser("USER2", 35);
		addUser("USER3", 35);
		addUser("USER4", 38);

		List<UserStatistic> biggestTomatoes = StatisticManager.getInstance().getBiggestTomatoes();
		assertEquals(4, biggestTomatoes.size());

		assertEquals("USER4", biggestTomatoes.get(0).getUserNr());
		assertEquals("USER1", biggestTomatoes.get(3).getUserNr());

	}

	@Test
	public void testAdd() {
		StatisticManager manager = StatisticManager.getInstance();

		User user = new User("USER5");
		manager.record(user, 0, 0);

		assertEquals(0, manager.getTotalTime(user));

		user.setTomatoTime(13);
		manager.record(user, 13, 0);
		assertEquals(13, manager.getTotalTime(user));

		user.setTomatoTime(8);
		user.setRemainingTime(5);
		manager.record(user, 8, 5);
		assertEquals(16, manager.getTotalTime(user));

		user.setTomatoTime(22);
		user.setRemainingTime(8);
		manager.record(user, 16, 8);
		assertEquals(24, manager.getTotalTime(user));

	}

	private void addUser(String nr, int remainingTime) {
		StatisticManager manager = StatisticManager.getInstance();
		User user = new User(nr);
		user.setRemainingTime(remainingTime);
		manager.record(user, 0, remainingTime);
	}

}
