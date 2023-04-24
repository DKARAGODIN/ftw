package pro.karagodin.models;

import lombok.Getter;
import lombok.Setter;
import pro.karagodin.ai_system.PlayerStrategy;
import pro.karagodin.output.Printer;
import pro.karagodin.time.TimeInterval;


/**
 * A game object that handled by users
 */
@Setter
@Getter
public class Player extends Mob {

    private int level = 1;
    private int xp = 0;
    private Inventory inventory = new Inventory();
    private boolean inventoryMode = false;
    private boolean wantsToContinuePlaying = true;

    public Player(int hp, int maxHp, TimeInterval pace, Printer printer) {
        super(hp, maxHp, pace, new PlayerStrategy(printer));
        this.view = '@';
    }

    public void quitFromGame() {
        wantsToContinuePlaying = false;
    }
}
