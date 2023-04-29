package pro.karagodin.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import pro.karagodin.ai_system.PlayerStrategy;
import pro.karagodin.output.Printer;
import pro.karagodin.time.TimeMoment;

class MobTest {
    @Test
    public void testConstructor() {

        Mob mob1 = new Mob(10, 10, new TimeMoment(100), new PlayerStrategy(new Printer()));
        assertEquals(10, mob1.getHp(), 0.001);
        assertEquals(10, mob1.getMaxHp());

        Mob mob2 = new Mob(5, 10, new TimeMoment(10), new PlayerStrategy(new Printer()));
        assertEquals(5, mob2.getHp(), 0.001);
        assertEquals(10, mob2.getMaxHp());
    }
}