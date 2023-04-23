package pro.karagodin.time;

public class TimeMoment implements Comparable<TimeMoment> {
    private long timeInMs;

    public TimeMoment() {
        timeInMs = new Timer().getCurrentTime();
    }

    private TimeMoment(long time) {
        timeInMs = time;
    }

    public TimeMoment after(TimeInterval interval) {
        return new TimeMoment(timeInMs + interval.getTimeInMs());
    }

    public long deltaWithCurrentTime() {
        return timeInMs - new Timer().getCurrentTime();
    }

    @Override
    public int compareTo(TimeMoment timeMoment) {
        return (int)(this.timeInMs - timeMoment.timeInMs);
    }
}
