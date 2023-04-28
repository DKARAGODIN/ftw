package pro.karagodin.output;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.ai_system.Strategy;
import pro.karagodin.models.Floor;
import pro.karagodin.models.Item;
import pro.karagodin.models.Mob;
import pro.karagodin.models.Wall;

public class DefaultMapStyler implements MapStyler {

    @Override
    public ConsoleCharacter stylizeMob(Mob mob) {
        if (mob == null) {
            return new ConsoleCharacter();
        } else if (mob.getType() == 0) {
            return new ConsoleCharacter('@', null, null, null, null, null);
        } else {
            return new ConsoleCharacter('A', null, null, null, null, null);
        }
    }

    @Override
    public ConsoleCharacter stylizeStrategy(Strategy strategy) {
        if (strategy == null) {
            return new ConsoleCharacter();
        } else {
            return switch (strategy.getTypeOfStrategy()) {
                case PlayerStrategy -> new ConsoleCharacter(null, TextColor.ANSI.RED, null, true, null, null);
                case AggressiveStrategy -> new ConsoleCharacter(null, TextColor.ANSI.RED, null, null, null, null);
                case PassiveStrategy -> new ConsoleCharacter(null, TextColor.ANSI.YELLOW, null, null, null, null);
                case CowardStrategy -> new ConsoleCharacter(null, TextColor.ANSI.BLUE, null, null, null, null);
            };
        }
    }

    @Override
    public ConsoleCharacter stylizeWall(Wall wall) {
        if (wall == null) {
            return new ConsoleCharacter();
        } else {
            return new ConsoleCharacter('█', TextColor.ANSI.WHITE, null, null, null, null);
        }
    }

    @Override
    public ConsoleCharacter stylizeItem(Item item) {
        if (item == null) {
            return new ConsoleCharacter();
        } else {
            return switch (item.getType()) {
                case AttackingItem -> new ConsoleCharacter('/', TextColor.ANSI.RED, null, null, null, null);
                case ProtectingItem -> new ConsoleCharacter('%', TextColor.ANSI.BLUE, null, null, null, null);
                case RevitalizingItem -> new ConsoleCharacter('+', TextColor.ANSI.GREEN, null, null, null, null);
                case SpecialItem -> new ConsoleCharacter('*', TextColor.ANSI.MAGENTA, null, null, null, null);
            };
        }
    }

    @Override
    public ConsoleCharacter stylizeFloor(Floor floor) {
        if (floor == null) {
            return new ConsoleCharacter();
        } else {
            return new ConsoleCharacter(' ', null, null, null, null, null);
        }
    }
}
