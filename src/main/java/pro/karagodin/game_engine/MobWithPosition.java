package pro.karagodin.game_engine;

import java.io.IOException;

import pro.karagodin.ai_system.Action;
import pro.karagodin.models.Map;
import pro.karagodin.models.Mob;

public class MobWithPosition {

    private final Mob mob;
    private final Coordinate position;

    public MobWithPosition(Map map, Coordinate position) {
        this.mob = map.getCell(position).getUnit();
        this.position = position;
    }

    public Mob getMob() {
        return mob;
    }

    public Coordinate getPosition() {
        return position;
    }

    public Action getNextAction(Map map) throws IOException {
        return mob.getNextAction(this, map);
    }
}
