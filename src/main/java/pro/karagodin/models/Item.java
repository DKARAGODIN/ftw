package pro.karagodin.models;

import java.util.Map;

import com.googlecode.lanterna.TextColor;
import lombok.Getter;
import pro.karagodin.output.CIDrowable;

/**
 * A game object that has no behavior but affects the player's characteristics
 * Maximum number of characteristics affected is 8 due to GUI limitations
 */
@Getter
public class Item implements CIDrowable {

    private final Map<Modifier, Integer> itemModifiers;
    private final char view;

    public Item(Map<Modifier, Integer> itemModifiers, char view) {
        this.itemModifiers = itemModifiers;
        this.view = view;
    }

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.GREEN;
    }

    @Override
    public TextColor getBackground() {
        return null;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemModifiers=" + itemModifiers +
                ", view=" + view +
                '}';
    }

    public enum Modifier implements Comparable<Modifier> {
        MAX_HP("Maximum HP", 5, 10),
        REGEN_HP("HP regeneration", 0.5, 1),
        ATTACK("Attack", 0.3, 1),
        DEFENCE("Defence", 0.3, 1),
        MIN_DAMAGE("Min damage", 2, 5),
        MAX_DAMAGE("Max damage", 2, 5),
        MAX_STAMINA("Max stamina", 2, 10),
        REGEN_STAMINA("Stamina regeneration", 0.5, 1),
        SPEED("Speed", 0.5, 1)
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
