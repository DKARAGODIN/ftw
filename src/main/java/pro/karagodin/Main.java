package pro.karagodin;

import java.io.IOException;

import pro.karagodin.game_engine.Game;

public class Main {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.start();
    }
}
