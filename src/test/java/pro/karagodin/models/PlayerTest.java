package pro.karagodin.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    public void testConstructor() {
        Cell cell = new Cell(42, 24);

        Player player1 = new Player(10, cell);
        assertEquals(10, player1.getHp(), 0.001);
        assertEquals(10, player1.getMaxHp());
        assertEquals(cell, player1.getPosition());
        assertEquals(1, player1.getLevel());
        assertEquals(0, player1.getXp());

        Player player2 = new Player(5, 10, cell);
        assertEquals(5, player2.getHp(), 0.001);
        assertEquals(10, player2.getMaxHp());
        assertEquals(cell, player2.getPosition());
        assertEquals(1, player2.getLevel());
        assertEquals(0, player2.getXp());
    }
}