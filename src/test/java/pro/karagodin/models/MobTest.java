package pro.karagodin.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import pro.karagodin.strategies.PlayerStrategy;
import pro.karagodin.output.IOAdapter;
import pro.karagodin.time.TimeMoment;

import java.util.List;

class MobTest {
    @Test
    public void testConstructor() {

        Mob mob1 = new Mob(10, 10, 0, 0, 0, 0, new TimeMoment(100), new PlayerStrategy(new IOAdapter()), 'A', List.of());
        assertEquals(10, mob1.getHp());
        assertEquals(10, mob1.getMaxHp());

        Mob mob2 = new Mob(5, 10, 0, 0, 0, 0, new TimeMoment(10), new PlayerStrategy(new IOAdapter()), 'A', List.of());
        assertEquals(5, mob2.getHp());
        assertEquals(10, mob2.getMaxHp());
    }
}