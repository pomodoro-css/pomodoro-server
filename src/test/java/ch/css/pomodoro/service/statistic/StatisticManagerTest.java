package ch.css.pomodoro.service.statistic;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import ch.css.pomodoro.service.dto.Tomato;
import ch.css.pomodoro.service.dto.TomatoTerminationReason;
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
		manager.add(new Tomato(user, TomatoTerminationReason.TERMINATED_DUE_PROCESS));

		assertEquals(0, manager.getTotalTime(user.getNr()));

		user.setTomatoTime(20);
		user.setRemainingTime(7);
		manager.add(new Tomato(user, TomatoTerminationReason.TERMINATED_DUE_PROCESS));
		assertEquals(13, manager.getTotalTime(user.getNr()));

		user.setTomatoTime(8);
		user.setRemainingTime(3);
		manager.add(new Tomato(user, TomatoTerminationReason.TERMINATED_DUE_PROCESS));
		assertEquals(18, manager.getTotalTime(user.getNr()));

	}

	private void addUser(String nr, int remainingTime) {
		StatisticManager manager = StatisticManager.getInstance();
		User user = new User(nr);
		user.setRemainingTime(remainingTime);
		manager.add(new Tomato(user, TomatoTerminationReason.TERMINATED_DUE_PROCESS));
	}

}
