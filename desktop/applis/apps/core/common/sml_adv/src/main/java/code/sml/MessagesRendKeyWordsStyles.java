package code.sml;

public final class MessagesRendKeyWordsStyles {
    private MessagesRendKeyWordsStyles() {
    }
    public static RendKeyWordsStyles init() {
        RendKeyWordsStyles s_ = new RendKeyWordsStyles();
        s_.setStyleAttrFontFam("font-family");
        s_.setStyleAttrFontSize("font-size");
        s_.setStyleAttrColor("color");
        s_.setStyleAttrBackground("background");
        s_.setStyleAttrBorder("border");
        s_.setStyleValueRgb("rgb");
        s_.setStyleValueRed("red");
        s_.setStyleValueGreen("green");
        s_.setStyleValueBlue("blue");
        s_.setStyleValueYellow("yellow");
        s_.setStyleValueCyan("cyan");
        s_.setStyleValueMagenta("magenta");
        s_.setStyleValueBlack("black");
        s_.setStyleValueGrey("grey");
        s_.setStyleValueWhite("white");
        s_.setStyleUnitPx("px");
        s_.setStyleUnitEm("em");
        s_.setStyleUnitSolid("solid");
        return s_;
    }
}
