package pro.karagodin.models;

import com.googlecode.lanterna.TextColor;
import lombok.Getter;
import lombok.Setter;
import pro.karagodin.ai_system.Strategy;
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

    public Mob(int hp, int maxHp, TimeInterval pace, Strategy strategy) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.pace = pace;
        this.strategy = strategy;
    }
    protected char view = 'A';
    protected TextColor color = TextColor.ANSI.WHITE;
    public char getView(){
        return view;
    }

    @Override
    public TextColor getForeground() {
        return color;
    }

    @Override
    public TextColor getBackground() {
        return null;
    }
}
