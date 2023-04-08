package pro.karagodin;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;


public class Main {
    public static void main(String[] args) throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        try (Screen screen = terminalFactory.createScreen()) {
            screen.startScreen();
            int col = 10;
            int row = 10;
            TextCharacter c = TextCharacter.fromCharacter('!', TextColor.ANSI.RED, TextColor.ANSI.GREEN)[0];
            screen.setCharacter(col, row, c);

            while (true) {
                if (false) break;

                screen.doResizeIfNecessary();

                screen.clear();

                KeyStroke key = screen.pollInput();
                if (key != null) {
                    switch (key.getKeyType()) {
                        case ArrowLeft -> col--;
                        case ArrowRight -> col++;
                        case ArrowDown -> row++;
                        case ArrowUp -> row--;
                    }
                }

                screen.setCharacter(col, row, c);
                screen.refresh();
                Thread.sleep(5);
            }

            screen.stopScreen();
        }
        catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
