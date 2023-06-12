package pro.karagodin.output;

import com.googlecode.lanterna.TextColor;

/**
 * Object that printer can draw on map
 */
public interface CIDrowable {
    char getView();

    TextColor getForeground();

    TextColor getBackground();
}
