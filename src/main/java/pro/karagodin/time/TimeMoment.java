package pro.karagodin.time;

import java.util.Date;

public class TimeMoment implements Comparable<TimeMoment> {
    private long timeInMs;

    public TimeMoment() {
        timeInMs = new Date().getTime();
    }

    private TimeMoment(long time) {
        timeInMs = time;
    }

    public TimeMoment after(TimeInterval interval) {
        return new TimeMoment(timeInMs + interval.getTimeInMs());
    }

    public long deltaWithCurrentTime() {
        return timeInMs - new Date().getTime();
    }

    @Override
    public int compareTo(TimeMoment timeMoment) {
        return (int)(this.timeInMs - timeMoment.timeInMs);
    }
}
