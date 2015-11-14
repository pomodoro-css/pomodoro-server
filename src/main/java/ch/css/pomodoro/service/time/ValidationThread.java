package ch.css.pomodoro.service.time;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.css.pomodoro.service.config.Config;
import ch.css.pomodoro.service.dto.UserStatistic;
import ch.css.pomodoro.service.helper.JSonConverter;
import ch.css.pomodoro.service.statistic.StatisticManager;
import ch.css.pomodoro.service.websocket.BroadcastSocket;

public class ValidationThread extends Thread {

	private UserManager manager;
	private Config config;
	private StatisticManager statistic;

	public ValidationThread(UserManager manager, Config config, StatisticManager statistic) {
		this.manager = manager;
		this.config = config;
		this.statistic = statistic;
	}

	public void run() {
		List<UserStatistic> userStatistics = clone(statistic.getBiggestTomatoes());

		while (!Thread.currentThread().isInterrupted()) {
			manager.validateUsers();

			if (!userStatistics.equals(statistic.getBiggestTomatoes())) {
				userStatistics = clone(statistic.getBiggestTomatoes());
				
				BroadcastSocket.broadcast(JSonConverter.getJSonObject("newBiggestTomato", userStatistics));
			}

			try {
				Thread.sleep(config.getTimerInMillis());
			} catch (InterruptedException e) {
				throw new RuntimeException(e.getMessage());
			}
		}

	}

	private ArrayList<UserStatistic> clone(List<UserStatistic> origList) {
		ArrayList<UserStatistic> clonedList = new ArrayList<UserStatistic>();

		for (UserStatistic userStatistic : origList) {
			try {
				clonedList.add((UserStatistic) userStatistic.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}

		return clonedList;
	}
}
