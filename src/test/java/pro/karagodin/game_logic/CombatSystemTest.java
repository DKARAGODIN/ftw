package pro.karagodin.game_logic;

import org.junit.jupiter.api.Test;
import pro.karagodin.ai_system.StatueStrategy;
import pro.karagodin.models.Mob;
import pro.karagodin.time.TimeMoment;

import static org.junit.jupiter.api.Assertions.*;

class CombatSystemTest {

    private final CombatSystem combatSystem = new CombatSystem();

    @Test
    public void test1_no_modifier() {
        Mob m1 = new Mob(100, 100, new TimeMoment(1), new StatueStrategy());
        m1.setMinDamage(10);
        m1.setMaxDamage(10);
        Mob m2 = new Mob(100, 100, new TimeMoment(1), new StatueStrategy());
        m2.setMinDamage(20);
        m2.setMaxDamage(20);
        combatSystem.attack(m1, m2);
        assertEquals(80, m1.getHp());
        assertEquals(90, m2.getHp());
    }

    @Test
    public void test2_no_modifier() {
        Mob m1 = new Mob(100, 100, new TimeMoment(1), new StatueStrategy());
        m1.setMinDamage(100);
        m1.setMaxDamage(110);
        Mob m2 = new Mob(100, 100, new TimeMoment(1), new StatueStrategy());
        m2.setMinDamage(20);
        m2.setMaxDamage(20);
        combatSystem.attack(m1, m2);
        assertEquals(100, m1.getHp());
        assertEquals(0, m2.getHp());
    }

    @Test
    public void test3_no_modifier() {
        Mob m1 = new Mob(100, 100, new TimeMoment(1), new StatueStrategy());
        m1.setMinDamage(1);
        m1.setMaxDamage(99);
        Mob m2 = new Mob(100, 100, new TimeMoment(1), new StatueStrategy());
        m2.setMinDamage(100);
        m2.setMaxDamage(100);
        combatSystem.attack(m1, m2);
        assertEquals(0, m1.getHp());
        assertTrue(m2.getHp() < 100 && m2.getHp() > 0);
    }

    @Test
    public void test1_with_modifier() {
        Mob m1 = new Mob(100, 100, new TimeMoment(1), new StatueStrategy());
        m1.setMinDamage(10);
        m1.setMaxDamage(10);
        m1.setAttack(20);
        Mob m2 = new Mob(100, 100, new TimeMoment(1), new StatueStrategy());
        m2.setMinDamage(0);
        m2.setMaxDamage(0);
        combatSystem.attack(m1, m2);
        assertEquals(100, m1.getHp());
        assertEquals(80, m2.getHp());
    }

    @Test
    public void test2_with_modifier() {
        Mob m1 = new Mob(100, 100, new TimeMoment(1), new StatueStrategy());
        m1.setMinDamage(10);
        m1.setMaxDamage(10);
        m1.setAttack(0);
        Mob m2 = new Mob(100, 100, new TimeMoment(1), new StatueStrategy());
        m2.setMinDamage(0);
        m2.setMaxDamage(0);
        m2.setDefence(20);
        combatSystem.attack(m1, m2);
        assertEquals(100, m1.getHp());
        assertEquals(95, m2.getHp());
    }

    @Test
    public void test3_with_modifier() {
        Mob m1 = new Mob(100, 100, new TimeMoment(1), new StatueStrategy());
        m1.setMinDamage(10);
        m1.setMaxDamage(10);
        m1.setAttack(100);
        Mob m2 = new Mob(100, 100, new TimeMoment(1), new StatueStrategy());
        m2.setMinDamage(0);
        m2.setMaxDamage(0);
        m2.setDefence(10);
        combatSystem.attack(m1, m2);
        assertEquals(100, m1.getHp());
        assertEquals(60, m2.getHp());
    }

    @Test
    public void test4_with_modifier() {
        Mob m1 = new Mob(100, 100, new TimeMoment(1), new StatueStrategy());
        m1.setMinDamage(10);
        m1.setMaxDamage(10);
        m1.setAttack(0);
        Mob m2 = new Mob(100, 100, new TimeMoment(1), new StatueStrategy());
        m2.setMinDamage(0);
        m2.setMaxDamage(0);
        m2.setDefence(100);
        combatSystem.attack(m1, m2);
        assertEquals(100, m1.getHp());
        assertEquals(98, m2.getHp());
    }
}