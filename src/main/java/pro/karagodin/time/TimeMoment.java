package pro.karagodin.time;

import java.util.Date;

/**
 * Time moment in milliseconds
 */
public class TimeMoment implements Comparable<TimeMoment> {
    private long timeInMs;

    public TimeMoment() {
        timeInMs = new Date().getTime();
    }

    public TimeMoment(long time) {
        timeInMs = time;
    }

    public TimeMoment after(TimeMoment interval) {
        return new TimeMoment(timeInMs + interval.getTimeInMs());
    }

    public long deltaWithCurrentTime() {
        return timeInMs - new Date().getTime();
    }

    @Override
    public int compareTo(TimeMoment timeMoment) {
        return (int)(this.timeInMs - timeMoment.timeInMs);
    }

    public long getTimeInMs() {
        return timeInMs;
    }

    public void setTimeInMs(long timeInMs) {
        this.timeInMs = timeInMs;
    }
}
