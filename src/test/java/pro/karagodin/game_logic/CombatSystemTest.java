package pro.karagodin.game_logic;

import org.junit.jupiter.api.Test;
import pro.karagodin.strategies.StatueStrategy;
import pro.karagodin.models.Mob;
import pro.karagodin.time.TimeMoment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombatSystemTest {

    private final CombatSystem combatSystem = new CombatSystem();

    @Test
    public void test1_no_modifier() {
        Mob m1 = new Mob(100, 100, 0, 0, 10, 10, new TimeMoment(1), new StatueStrategy(), 'A', List.of());
        Mob m2 = new Mob(100, 100, 0, 0, 20, 20, new TimeMoment(1), new StatueStrategy(), 'A', List.of());
        combatSystem.attack(m1, m2, null);
        combatSystem.attack(m2, m1, null);
        assertEquals(80, m1.getHp());
        assertEquals(90, m2.getHp());
    }

    @Test
    public void test2_no_modifier() {
        Mob m1 = new Mob(100, 100, 0, 0, 100, 110, new TimeMoment(1), new StatueStrategy(), 'A', List.of());
        Mob m2 = new Mob(100, 100, 0, 0, 20, 20, new TimeMoment(1), new StatueStrategy(), 'A', List.of());
        combatSystem.attack(m1, m2, null);
        assertEquals(100, m1.getHp());
        assertTrue(m2.isKilled());
    }

    @Test
    public void test3_no_modifier() {
        Mob m1 = new Mob(100, 100, 0, 0, 1, 99, new TimeMoment(1), new StatueStrategy(), 'A', List.of());
        Mob m2 = new Mob(100, 100, 0, 0, 100, 100, new TimeMoment(1), new StatueStrategy(), 'A', List.of());
        combatSystem.attack(m1, m2, null);
        assertFalse(m2.isKilled());
        combatSystem.attack(m2, m1, null);
        assertTrue(m1.isKilled());
        assertTrue(m2.getHp() < 100 && m2.getHp() > 0);
    }

    @Test
    public void test1_with_modifier() {
        Mob m1 = new Mob(100, 100, 20, 0, 10, 10, new TimeMoment(1), new StatueStrategy(), 'A', List.of());
        Mob m2 = new Mob(100, 100, 0, 0, 0, 0, new TimeMoment(1), new StatueStrategy(), 'A', List.of());
        combatSystem.attack(m1, m2, null);
        assertEquals(80, m2.getHp());
    }

    @Test
    public void test2_with_modifier() {
        Mob m1 = new Mob(100, 100, 0, 0, 10, 10, new TimeMoment(1), new StatueStrategy(), 'A', List.of());
        Mob m2 = new Mob(100, 100, 0, 20, 0, 0, new TimeMoment(1), new StatueStrategy(), 'A', List.of());
        combatSystem.attack(m1, m2, null);
        assertEquals(95, m2.getHp());
    }

    @Test
    public void test3_with_modifier() {
        Mob m1 = new Mob(100, 100, 100, 0, 10, 10, new TimeMoment(1), new StatueStrategy(), 'A', List.of());
        Mob m2 = new Mob(100, 100, 0, 10, 0, 0, new TimeMoment(1), new StatueStrategy(), 'A', List.of());
        combatSystem.attack(m1, m2, null);
        assertEquals(60, m2.getHp());
    }

    @Test
    public void test4_with_modifier() {
        Mob m1 = new Mob(100, 100, 0, 0, 10, 10, new TimeMoment(1), new StatueStrategy(), 'A', List.of());
        Mob m2 = new Mob(100, 100, 0, 100, 0, 0, new TimeMoment(1), new StatueStrategy(), 'A', List.of());
        combatSystem.attack(m1, m2, null);
        assertEquals(98, m2.getHp());
    }
}