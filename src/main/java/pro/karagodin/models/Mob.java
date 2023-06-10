package pro.karagodin.models;

import java.io.IOException;
import java.util.ArrayList;

import com.googlecode.lanterna.TextColor;
import lombok.Getter;
import lombok.Setter;
import pro.karagodin.ai_system.Action;
import pro.karagodin.ai_system.Effect;
import pro.karagodin.ai_system.Strategy;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.output.CIDrowable;
import pro.karagodin.time.TimeMoment;

/**
 * A game object that can move around and affect gameplay
 */
@Setter
@Getter
public class Mob implements CIDrowable {

    protected int hp;
    protected int maxHp;
    protected int attack;
    protected int defence;
    protected int minDamage;
    protected int maxDamage;
    protected int stamina;
    protected int maxStamina;

    protected TimeMoment pace;
    protected Strategy strategy;
    protected char view = 'A';
    protected TextColor color = TextColor.ANSI.WHITE;
    protected ArrayList<Effect> attackEffects = new ArrayList<>();

    public Mob(int hp, int maxHp, TimeMoment pace, Strategy strategy) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.pace = pace;
        this.strategy = strategy;
    }

    public Mob(int hp, int maxHp, TimeMoment pace, Strategy strategy, ArrayList<Effect> attackEffects) {
        this(hp, maxHp, pace, strategy);
        this.attackEffects = attackEffects;
    }

    public char getView() {
        return strategy.getView() != 0 ? strategy.getView() : view;
    }

    @Override
    public TextColor getForeground() {
        return strategy.getForeground() != null ? strategy.getForeground() : color;
    }

    @Override
    public TextColor getBackground() {
        return null;
    }

    public Action getNextAction(MobWithPosition mobWithPosition, Map map) throws IOException {
        Action action = strategy.getNextAction(mobWithPosition, map);
        strategy = strategy.nextStrategy();
        return action;
    }

    public boolean isKilled() {
        return hp <= 0;
    }
}
