package pro.karagodin.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.TextColor;
import lombok.Getter;
import lombok.Setter;
import pro.karagodin.actions.Action;
import pro.karagodin.effects.Effect;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.output.CIDrowable;
import pro.karagodin.strategies.Strategy;
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
    protected List<Effect> attackEffects;

    public Mob(int maxHp, int attack, int defence, int minDamage, int maxDamage, TimeMoment pace, Strategy strategy, char view, List<Effect> attackEffects) {
        this(maxHp, maxHp, attack, defence, minDamage, maxDamage, pace, strategy, view, attackEffects);
    }

    public Mob(int hp, int maxHp, int attack, int defence, int minDamage, int maxDamage, TimeMoment pace, Strategy strategy, char view, List<Effect> attackEffects) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.attack = attack;
        this.defence = defence;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.pace = pace;
        this.strategy = strategy;
        this.view = view;
        this.attackEffects = new ArrayList<>(attackEffects);
    }

    public char getView() {
        return strategy.getView() != 0 ? strategy.getView() : view;
    }

    @Override
    public TextColor getForeground() {
        return strategy.getForeground() != null ? strategy.getForeground() : TextColor.ANSI.WHITE;
    }

    @Override
    public TextColor getBackground() {
        return null;
    }

    public Action getNextAction(MobWithPosition mobWithPosition, Map map) throws IOException {
        Action noAction = strategy.getNextAction(mobWithPosition, map);
        strategy = strategy.nextStrategy();
        return noAction;
    }

    public boolean isKilled() {
        return hp <= 0;
    }

    public Mob cloneMob() {
        return new Mob(hp, hp, attack, defence, minDamage, maxDamage, pace, strategy.cloneStrategy(), view, attackEffects);
    }
}
