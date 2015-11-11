package ch.css.pomodoro.service.time;

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
		List<UserStatistic> userStatistics = statistic.getBiggestTomatoes();
		
		while (!Thread.currentThread().isInterrupted()) {
			manager.validateUsers();
			
			// TODO Vergleich sollte Zeit einschliessen
			if(!userStatistics.equals(statistic.getBiggestTomatoes())){
				userStatistics = statistic.getBiggestTomatoes();
				
				BroadcastSocket.broadcast(JSonConverter.getJSonObject("newBiggestTomato", userStatistics));
			}
			
			try {
				Thread.sleep(config.getTimerInMillis());
			} catch (InterruptedException e) {
				throw new RuntimeException(e.getMessage());
			}
		}

	}
}
