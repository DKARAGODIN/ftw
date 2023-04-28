package pro.karagodin.output;

import pro.karagodin.ai_system.Strategy;
import pro.karagodin.models.Cell;
import pro.karagodin.models.Floor;
import pro.karagodin.models.Item;
import pro.karagodin.models.Mob;
import pro.karagodin.models.Wall;

public interface MapStyler {

    public ConsoleCharacter stylizeMob(Mob mob);

    public ConsoleCharacter stylizeStrategy(Strategy strategy);

    public ConsoleCharacter stylizeWall(Wall wall);

    public ConsoleCharacter stylizeItem(Item item);

    public ConsoleCharacter stylizeFloor(Floor floor);

    public default ConsoleCharacter stylizeCell(Cell cell) {
        ConsoleCharacter character = new ConsoleCharacter();
        if (cell == null) {
            return character;
        }
        if (cell.getUnit() != null) {
            character = character.compose(stylizeMob(cell.getUnit()).overwrite(stylizeStrategy(cell.getUnit().getStrategy())));
        }
        if (cell.getWall() != null) {
            character = character.compose(stylizeWall(cell.getWall()));
        }
        if (cell.getItem() != null) {
            character = character.compose(stylizeItem(cell.getItem()));
        }
        if (cell.getFloor() != null) {
            character = character.compose(stylizeFloor(cell.getFloor()));
        }
        return character;
    }
}
