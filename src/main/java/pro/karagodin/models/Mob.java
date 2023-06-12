package pro.karagodin.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    protected TimeMoment pace;
    protected Strategy strategy;
    protected char view;
    protected TextColor color;
    protected List<Effect> attackEffects;

    public Mob(int hp, int maxHp, int attack, int defence, int minDamage, int maxDamage, TimeMoment pace, Strategy strategy) {
        this(hp, maxHp, attack, defence, minDamage, maxDamage, pace, strategy, 'A', TextColor.ANSI.WHITE, List.of());
    }

    public Mob(int hp, int maxHp, int attack, int defence, int minDamage, int maxDamage, TimeMoment pace, Strategy strategy, char view, TextColor color, List<Effect> attackEffects) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.attack = attack;
        this.defence = defence;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.pace = pace;
        this.strategy = strategy;
        this.view = view;
        this.color = color;
        this.attackEffects = new ArrayList<>(attackEffects);
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

    public Mob cloneMob() {
        return new Mob(hp, maxHp, attack, defence, minDamage, maxDamage, pace, strategy.cloneStrategy(), view, color, attackEffects);
    }
}
