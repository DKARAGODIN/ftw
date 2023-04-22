package pro.karagodin.game_engine;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pro.karagodin.models.Map;
import pro.karagodin.models.Mob;
import pro.karagodin.time.TimeMoment;

import java.util.PriorityQueue;

public class Timeline {

    private static class MobInfo implements Comparable<MobInfo> {
        public Mob mob;
        public Coordinate coord;
        public TimeMoment actionTime;

        public MobInfo(Mob mob, Coordinate coord, TimeMoment actionTime) {
            this.mob = mob;
            this.coord = coord;
            this.actionTime = actionTime;
        }

        @Override
        public int compareTo(@NotNull MobInfo mobInfo) {
            return actionTime.compareTo(mobInfo.actionTime);
        }
    }

    private PriorityQueue<MobInfo> mobsWithTimes;
    private TimeMoment nextTimeForLastMob;

    public Timeline(Map map) {
        mobsWithTimes = new PriorityQueue<>();
        for (int x = 0; x < map.getHeight(); x++) {
            for (int y = 0; y < map.getWidth(); y++) {
                Mob mob = map.getCell(x, y).getUnit();
                if (mob != null) {
                    mobsWithTimes.add(new MobInfo(mob, new Coordinate(x, y), new TimeMoment()));
                }
            }
        }
    }

    public @Nullable Coordinate getMobForDoingAction() {
        MobInfo info = mobsWithTimes.peek();
        if (info.actionTime.deltaWithCurrentTime() > 0) {
            return null;
        }
        mobsWithTimes.poll();
        nextTimeForLastMob = info.actionTime.after(info.mob.getPace());
        return info.coord;
    }

    public void addUpdatedMob(Mob mob, Coordinate coord) {
        mobsWithTimes.add(new MobInfo(mob, coord, nextTimeForLastMob));
    }

    public long getDeltaTimeForAction() {
        return mobsWithTimes.peek().actionTime.deltaWithCurrentTime();
    }

}
