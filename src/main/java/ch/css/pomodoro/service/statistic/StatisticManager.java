package ch.css.pomodoro.service.statistic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.css.pomodoro.service.dto.User;
import ch.css.pomodoro.service.dto.UserStatistic;

public class StatisticManager {

	private static StatisticManager instance;

	private Map<String, UserStatistic> statisticsRepo;

	private StatisticManager() {
		statisticsRepo = new HashMap<>();
	}

	public static StatisticManager getInstance() {
		if (instance == null) {
			instance = new StatisticManager();
		}
		return instance;
	}

	public int getTotalTime(User user) {
		return getStatistic(user).getTotalTime();
	}

	public void record(User user, int oldRemainingTime, int newRemainingTime) {
		UserStatistic statistic = getStatistic(user);
		int totalTime = statistic.getTotalTime();
		if (newRemainingTime <= 0) {
			totalTime += oldRemainingTime;
		} else {
			totalTime += oldRemainingTime - newRemainingTime;
		}
		statistic.setTotalTime(totalTime);
	}

	public List<UserStatistic> getLooserTomatoes() {
		List<UserStatistic> statisticSortable = sortLooser();
		return getMaxList(statisticSortable);
	}

	public List<UserStatistic> getBiggestTomatoes() {
		List<UserStatistic> statisticSortable = sortBiggest();
		return getMaxList(statisticSortable);
	}

	private List<UserStatistic> getMaxList(List<UserStatistic> statisticSortable) {
		int max = statisticSortable.size() < 5 ? statisticSortable.size() : 5;
		return statisticSortable.subList(0, max);
	}

	private UserStatistic getStatistic(User user) {
		UserStatistic statistic = statisticsRepo.get(user.getNr());
		if (statistic == null) {
			statistic = new UserStatistic(user);
			statisticsRepo.put(user.getNr(), statistic);
		}
		return statistic;
	}

	private List<UserStatistic> sortLooser() {
		List<UserStatistic> statisticSortable = new ArrayList<>(statisticsRepo.values());
		Collections.sort(statisticSortable, new Comparator<UserStatistic>() {
			@Override
			public int compare(UserStatistic stat1, UserStatistic stat2) {
				if (stat1.getTotalTime() > stat2.getTotalTime()) {
					return 1;
				}
				return -1;
			}
		});
		return statisticSortable;
	}

	private List<UserStatistic> sortBiggest() {
		List<UserStatistic> statisticSortable = new ArrayList<>(statisticsRepo.values());
		Collections.sort(statisticSortable, new Comparator<UserStatistic>() {
			@Override
			public int compare(UserStatistic stat1, UserStatistic stat2) {
				if (stat1.getTotalTime() > stat2.getTotalTime()) {
					return -1;
				}
				return 1;
			}
		});
		return statisticSortable;
	}

}
