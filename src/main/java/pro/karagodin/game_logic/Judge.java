package pro.karagodin.game_logic;

import com.googlecode.lanterna.input.KeyStroke;
import pro.karagodin.models.Player;

public class Judge {
    /**
     *
     * @param key - not null;
     */
    public void doPlayerAction(KeyStroke key, Player player) {
        switch (key.getKeyType()) {
            case ArrowLeft:
            case ArrowRight:
            case ArrowDown:
            case ArrowUp:
            case Character: {
                switch (key.getCharacter()) {
                    case 'q':
                    case 'i':
                        player.setInventoryMode(true);
                    break;
                }
            }
            break;
        }
    }
}
