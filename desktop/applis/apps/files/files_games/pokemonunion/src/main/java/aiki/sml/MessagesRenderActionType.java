package aiki.sml;

import code.sml.util.TranslationsFile;

public final class MessagesRenderActionType {
    public static final String MOVE = "0";
    public static final String SWITCH = "1";
    public static final String HEALING = "2";
    public static final String NOTHING = "3";

    private MessagesRenderActionType() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(MOVE, "Use a move");
        e_.add(SWITCH, "Substitute");
        e_.add(HEALING, "Heal");
        e_.add(NOTHING, "Nothing");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(MOVE, "Attaquer");
        f_.add(SWITCH, "Remplacer");
        f_.add(HEALING, "Soigner");
        f_.add(NOTHING, "Rien");
        return f_;
    }
}
