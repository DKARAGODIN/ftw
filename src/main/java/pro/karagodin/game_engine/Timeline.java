package pro.karagodin.game_engine;

import java.util.PriorityQueue;

import pro.karagodin.models.Map;
import pro.karagodin.time.TimeMoment;

/**
 * Game time loop.
 */
public class Timeline {

    private final PriorityQueue<MobInfo> mobsWithTimes;
    private TimeMoment nextTimeForLastMob;

    public Timeline(Map map) {
        mobsWithTimes = new PriorityQueue<>();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                MobWithPosition mobAndCoord = new MobWithPosition(map, new Coordinate(x, y));
                if (mobAndCoord.getMob() != null) {
                    mobsWithTimes.add(new MobInfo(mobAndCoord, new TimeMoment()));
                }
            }
        }
    }

    private MobInfo peek() {
        while (mobsWithTimes.peek().mobAndCoord.getMob().isKilled()) {
            mobsWithTimes.poll();
        }
        return mobsWithTimes.peek();
    }

    /**
     * Get next mob in queue
     * @return
     */
    public MobWithPosition getMobForDoingAction() {
        if (getDeltaTimeForAction() > 0) {
            return null;
        }
        MobInfo info = mobsWithTimes.poll();
        nextTimeForLastMob = info.actionTime.after(info.mobAndCoord.getMob().getPace());
        return info.mobAndCoord;
    }

    public void addUpdatedMob(MobWithPosition mobAndCoord) {
        mobsWithTimes.add(new MobInfo(mobAndCoord, nextTimeForLastMob));
    }

    public long getDeltaTimeForAction() {
        return peek().actionTime.deltaWithCurrentTime();
    }

    private static class MobInfo implements Comparable<MobInfo> {
        public MobWithPosition mobAndCoord;
        public TimeMoment actionTime;

        public MobInfo(MobWithPosition mobAndCoord, TimeMoment actionTime) {
            this.mobAndCoord = mobAndCoord;
            this.actionTime = actionTime;
        }

        @Override
        public int compareTo(MobInfo mobInfo) {
            return actionTime.compareTo(mobInfo.actionTime);
        }
    }
}
