package pro.karagodin.models;

import java.io.IOException;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import pro.karagodin.ai_system.Action;
import pro.karagodin.ai_system.Effect;
import pro.karagodin.ai_system.Strategy;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.time.TimeInterval;

/**
  A game object that can move around and affect gameplay
 */
@Setter
@Getter
public class Mob {
    protected int type;
    protected int hp;
    protected int maxHp;
    protected TimeInterval pace;
    protected Strategy strategy;
    protected ArrayList<Effect> attackEffects;

    public Mob(int type, int hp, int maxHp, TimeInterval pace, Strategy strategy) {
        this.type = type;
        this.hp = hp;
        this.maxHp = maxHp;
        this.pace = pace;
        this.strategy = strategy;
        this.attackEffects = new ArrayList<>();
    }

    public Mob(int type, int hp, int maxHp, TimeInterval pace, Strategy strategy, ArrayList<Effect> attackEffects) {
        this(type, hp, maxHp, pace, strategy);
        this.attackEffects = attackEffects;
    }

    public Action getNextAction(MobWithPosition mobWithPosition, Map map) throws IOException {
        Action action = strategy.getNextAction(mobWithPosition, map);
        strategy = strategy.getNextStrategy();
        return action;
    }
}
