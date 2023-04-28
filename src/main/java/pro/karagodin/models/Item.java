package pro.karagodin.models;

import lombok.Getter;
import lombok.Setter;

/**
 * A game object that has no behavior but affects the player's characteristics
 */
@Setter
@Getter
public class Item {
    protected int hpIncrement = 0;
    protected int attackIncrement = 0;
    protected TypeOfItem type;
}
