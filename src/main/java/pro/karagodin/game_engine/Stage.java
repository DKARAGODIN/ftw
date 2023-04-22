package pro.karagodin.game_engine;

import com.googlecode.lanterna.input.KeyStroke;
import pro.karagodin.game_logic.Judge;
import pro.karagodin.models.Map;
import pro.karagodin.models.Mob;
import pro.karagodin.models.Player;
import pro.karagodin.output.Printer;

import java.io.IOException;

import static java.lang.Math.max;

public class Stage {

    private Printer printer;
    private Judge judge;
    private Player player;
    private Map map;
    private Timeline timeline;

    public Stage(Printer printer, Player player) {
        this.printer = printer;
        this.judge = new Judge();
        this.player = player;
        this.map = new Map();
        this.timeline = new Timeline(this.map);
    }

    public StageEnd start() throws IOException {
        while (true) {
            if (timeline.getDeltaTimeForAction() > 0) {
                try {
                    Thread.sleep(Math.max(0, timeline.getDeltaTimeForAction()));
                } catch (InterruptedException ignore) {}
            } else {
                Coordinate mobCoord = timeline.getMobForDoingAction();
                KeyStroke key = printer.pressedKey();
                if (key != null) {
                    MapDiff diff = judge.doPlayerAction(key, mobCoord, map);
                    printer.updateCoordinates(map, diff);
                }
                //Mob mob = map.getCell(mobCoord).getUnit();
            }
            /*KeyStroke key = printer.pressedKey();
            if (key != null) {
                judge.doPlayerAction(key, player);
                printer.printPlayer(player);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }*/
        }
    }

    public static class StageEnd {
        public boolean quitGame = false;
        public boolean nextStage = false;

        public StageEnd(boolean quitGame, boolean nextStage) {
            this.quitGame = quitGame;
            this.nextStage = nextStage;
        }

        public StageEnd() {
        }
    }

}
