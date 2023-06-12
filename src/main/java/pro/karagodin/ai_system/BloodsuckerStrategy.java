package pro.karagodin.ai_system;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class BloodsuckerStrategy extends InvisibleStrategy {

    public BloodsuckerStrategy(double pursuitIndex, int criticalDistance) {
        super(new Strategy() {

            private final AttackAroundStrategy attackAroundStrategy = new AttackAroundStrategy();
            private final PursuitStrategy pursuitStrategy = new PursuitStrategy(pursuitIndex);

            @Override
            public Action getNextAction(MobWithPosition mobAndCoord, Map map) {
                Action attackAroundAction = attackAroundStrategy.getNextAction(mobAndCoord, map);
                return attackAroundAction != Action.DoNothing ? attackAroundAction : pursuitStrategy.getNextAction(mobAndCoord, map);
            }

            @Override
            public TextColor getForeground() {
                return attackAroundStrategy.getForeground();
            }
        }, criticalDistance);
    }
}
