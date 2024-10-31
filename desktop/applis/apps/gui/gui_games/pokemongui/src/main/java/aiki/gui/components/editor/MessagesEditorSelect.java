package aiki.gui.components.editor;

import aiki.db.*;
import code.sml.util.*;

public final class MessagesEditorSelect {
    private MessagesEditorSelect() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DataBase.DEF_GENDER_REP_FEMALE, "100% Female");
        e_.add(DataBase.DEF_GENDER_REP_MALE, "100% Male");
        e_.add(DataBase.DEF_GENDER_REP_MIXED, "Mixed");
        e_.add(DataBase.DEF_GENDER_REP_LEGENDARY, "Legendary");
        e_.add(DataBase.DEF_GENDER_REP_NO_GENDER, "No gender");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DataBase.DEF_GENDER_REP_FEMALE, "100% Femelle");
        f_.add(DataBase.DEF_GENDER_REP_MALE, "100% Mâle");
        f_.add(DataBase.DEF_GENDER_REP_MIXED, "Mixte");
        f_.add(DataBase.DEF_GENDER_REP_LEGENDARY, "Légendaire");
        f_.add(DataBase.DEF_GENDER_REP_NO_GENDER, "Pas de genre");
        return f_;
    }
}
