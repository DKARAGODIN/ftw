package pro.karagodin.output;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

public class ConsoleCharacter {
    private final Character symbol;
    private final TextColor frontColor;
    private final TextColor backColor;
    private final Boolean isBold;
    private final Boolean isItalic;
    private final Boolean isUnderlined;

    public ConsoleCharacter() {
        this.symbol = null;
        this.frontColor = null;
        this.backColor = null;
        this.isBold = null;
        this.isItalic = null;
        this.isUnderlined = null;
    }

    public ConsoleCharacter(Character symbol, TextColor frontColor, TextColor backColor, Boolean isBold, Boolean isItalic, Boolean isUnderlined) {
        this.symbol = symbol;
        this.frontColor = frontColor;
        this.backColor = backColor;
        this.isBold = isBold;
        this.isItalic = isItalic;
        this.isUnderlined = isUnderlined;
    }

    public TextCharacter toTextCharacter() {
        char nnSymbol = symbol != null ? symbol : ' ';
        TextColor nnFrontColor = frontColor != null ? frontColor : TextColor.ANSI.WHITE;
        TextColor nnBackColor = backColor != null ? backColor : TextColor.ANSI.BLACK;
        boolean nnIsBold = isBold != null ? isBold : false;
        boolean nnIsItalic = isItalic != null ? isItalic : false;
        boolean nnIsUnderlined = isUnderlined != null ? isUnderlined : false;
        if (nnIsBold) {
            if (nnIsItalic) {
                if (nnIsUnderlined) {
                    return new TextCharacter(nnSymbol, nnFrontColor, nnBackColor, SGR.BOLD, SGR.ITALIC, SGR.UNDERLINE);
                } else {
                    return new TextCharacter(nnSymbol, nnFrontColor, nnBackColor, SGR.BOLD, SGR.ITALIC);
                }
            } else {
                if (nnIsUnderlined) {
                    return new TextCharacter(nnSymbol, nnFrontColor, nnBackColor, SGR.BOLD, SGR.UNDERLINE);
                } else {
                    return new TextCharacter(nnSymbol, nnFrontColor, nnBackColor, SGR.BOLD);
                }
            }
        } else {
            if (nnIsItalic) {
                if (nnIsUnderlined) {
                    return new TextCharacter(nnSymbol, nnFrontColor, nnBackColor, SGR.ITALIC, SGR.UNDERLINE);
                } else {
                    return new TextCharacter(nnSymbol, nnFrontColor, nnBackColor, SGR.ITALIC);
                }
            } else {
                if (nnIsUnderlined) {
                    return new TextCharacter(nnSymbol, nnFrontColor, nnBackColor, SGR.UNDERLINE);
                } else {
                    return new TextCharacter(nnSymbol, nnFrontColor, nnBackColor);
                }
            }
        }
    }

    public ConsoleCharacter compose(ConsoleCharacter other) {
        Character symbol = null;
        TextColor frontColor = null;
        TextColor backColor = null;
        Boolean isBold = null;
        Boolean isItalic = null;
        Boolean isUnderlined = null;
        if (this.symbol != null) {
            symbol = this.symbol;
            frontColor = this.frontColor;
            isBold = this.isBold;
            isItalic = this.isItalic;
            isUnderlined = this.isUnderlined;
        } else if (other != null) {
            symbol = other.symbol;
            frontColor = other.frontColor;
            isBold = other.isBold;
            isItalic = other.isItalic;
            isUnderlined = other.isUnderlined;
        }
        if (this.backColor != null) {
            backColor = this.backColor;
        } else if (other != null) {
            backColor = other.backColor;
        }
        return new ConsoleCharacter(symbol, frontColor, backColor, isBold, isItalic, isUnderlined);
    }

    public ConsoleCharacter overwrite(ConsoleCharacter other) {
        Character symbol = this.symbol;
        TextColor frontColor = this.frontColor;
        TextColor backColor = this.backColor;
        Boolean isBold = this.isBold;
        Boolean isItalic = this.isItalic;
        Boolean isUnderlined = this.isUnderlined;
        if (other != null) {
            if (other.symbol != null) {
                symbol = other.symbol;
            }
            if (other.frontColor != null) {
                frontColor = other.frontColor;
            }
            if (other.backColor != null) {
                backColor = other.backColor;
            }
            if (other.isBold != null) {
                isBold = other.isBold;
            }
            if (other.isItalic != null) {
                isItalic = other.isItalic;
            }
            if (other.isUnderlined != null) {
                isUnderlined = other.isUnderlined;
            }
        }
        return new ConsoleCharacter(symbol, frontColor, backColor, isBold, isItalic, isUnderlined);
    }
}
