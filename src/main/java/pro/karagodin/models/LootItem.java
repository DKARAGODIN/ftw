package pro.karagodin.models;

import java.util.Map;

import com.googlecode.lanterna.TextColor;
import lombok.Getter;
import lombok.Setter;
import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_logic.Judge;

/**
 * A game object that has no behavior but affects the player's characteristics
 */
@Getter
public class LootItem implements LowerItem {

    private final Map<Modifier, Integer> itemModifiers;
    private final char view;
    private final int itemLevel;

    @Setter
    private boolean equipped;

    public LootItem(Map<Modifier, Integer> itemModifiers, int itemLevel, char view) {
        this.itemModifiers = itemModifiers;
        this.view = view;
        this.itemLevel = itemLevel;
    }

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.GREEN;
    }

    @Override
    public String toString() {
        return "SmallThing{" +
                "itemModifiers=" + itemModifiers +
                ", view=" + view +
                '}';
    }

    @Override
    public GameDiff use(Judge judge) {
        return judge.useLootItem(this);
    }

    public enum Modifier implements Comparable<Modifier> {
        MAX_HP("Maximum HP", 2, 10),
        //REGEN_HP("HP regeneration", 0.5, 1), -- Effect not implemented yet
        ATTACK("Attack", 0.7, 1),
        DEFENCE("Defence", 0.7, 1),
        MIN_DAMAGE("Min damage", 1.5, 5),
        MAX_DAMAGE("Max damage", 1.5, 5),
        SPEED("Speed", 1, 1),
        ;

        private final String description;
        private final double levelIncrease;
        private final int startValue;

        Modifier(String description, double levelIncrease, int startValue) {
            this.description = description;
            this.levelIncrease = levelIncrease;
            this.startValue = startValue;
        }

        public String getDescription() {
            return description;
        }

        public double getLevelIncrease() {
            return levelIncrease;
        }

        public int getStartValue() {
            return startValue;
        }
    }
}
