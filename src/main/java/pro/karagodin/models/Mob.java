package pro.karagodin.models;

import java.io.IOException;

import com.googlecode.lanterna.TextColor;
import lombok.Getter;
import lombok.Setter;
import pro.karagodin.ai_system.Action;
import pro.karagodin.ai_system.Strategy;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.output.CIDrowable;
import pro.karagodin.time.TimeInterval;

/**
  A game object that can move around and affect gameplay
 */
@Setter
@Getter
public class Mob implements CIDrowable {
    protected int hp;
    protected int maxHp;
    protected TimeInterval pace;
    protected Strategy strategy;
    protected char view = 'A';
    protected TextColor color = TextColor.ANSI.WHITE;

    public Mob(int hp, int maxHp, TimeInterval pace, Strategy strategy) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.pace = pace;
        this.strategy = strategy;
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
}
