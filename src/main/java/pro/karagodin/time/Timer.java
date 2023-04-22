package pro.karagodin.time;

import java.util.Date;

public class Timer {
    private Date date;

    public Timer() {
        date = new Date();
    }

    public long getCurrentTime() {
        return date.getTime();
    }
}
