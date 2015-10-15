package ch.css.pomodoro.service.statistic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.css.pomodoro.service.dto.Tomato;
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

	public int getTotalTime(String nr) {
		return getStatistic(nr.toUpperCase()).getTotalTime();
	}

	public void add(Tomato tomato) {
		UserStatistic statistic = getStatistic(tomato.getUserNr());
		int totalTime = statistic.getTotalTime() + tomato.getTomatoTime();
		if (tomato.getRemainingTime() > 0) {
			totalTime -= tomato.getRemainingTime();
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

	private UserStatistic getStatistic(String nr) {
		UserStatistic statistic = statisticsRepo.get(nr);
		if (statistic == null) {
			statistic = new UserStatistic(nr);
			statisticsRepo.put(nr, statistic);
		}
		return statistic;
	}

	private List<UserStatistic> sortLooser() {
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

	private List<UserStatistic> sortBiggest() {
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

}
