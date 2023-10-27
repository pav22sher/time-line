package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
    }

    public List<WorkTime> split(List<User> list) {
        List<WorkTime> workTimeList = new ArrayList<>();
        Map<Integer, TimeLineFlag> timelines = getTimeLine(list);
        var entries = timelines.entrySet().stream().toList();
        int size = entries.size();
        if(size == 1) {
            Integer fromDate = entries.get(0).getKey();
            addWorkTime(workTimeList, fromDate, fromDate, list);
        }
        for (int j = 1; j < size; j++) {
            var tmp0 = entries.get(j - 1);
            TimeLineFlag tmpf0 = tmp0.getValue();
            var tmp1 = entries.get(j);
            TimeLineFlag tmpf1 = tmp1.getValue();
            int fromDate = tmp0.getKey();
            int toDate = tmp1.getKey();
            if(tmpf0.isStartTime() && tmpf0.isEndTime()) addWorkTime(workTimeList, fromDate, fromDate, list);
            if(tmpf0.isEndTime()) fromDate++;
            if(tmpf1.isStartTime()) toDate--;
            addWorkTime(workTimeList, fromDate, toDate, list);
        }
        return workTimeList;
    }

    public void addWorkTime(List<WorkTime> workTimeList, int fromDate, int toDate, List<User> list) {
        List<String> names = new ArrayList<>();
        for (User user : list) {
            if (user.getStartTime() <= fromDate && user.getEndTime() >= toDate) {
                names.add(user.getName());
            }
        }
        if (!names.isEmpty()) {
            names.sort(String::compareToIgnoreCase);
            workTimeList.add(new WorkTime(fromDate, toDate, names));
        }
    }

    public Map<Integer, TimeLineFlag> getTimeLine(List<User> list) {
        Map<Integer, TimeLineFlag> timeline = new TreeMap<>();
        for (User user : list) {
            int startTime = user.getStartTime();
            TimeLineFlag startFlag = timeline.containsKey(startTime) ? timeline.get(startTime) : new TimeLineFlag();
            startFlag.setStartTime(true);
            timeline.put(startTime, startFlag);
            int endTime = user.getEndTime();
            TimeLineFlag endFlag = timeline.containsKey(endTime) ? timeline.get(endTime) : new TimeLineFlag();
            endFlag.setEndTime(true);
            timeline.put(endTime, endFlag);
        }
        return timeline;
    }
}