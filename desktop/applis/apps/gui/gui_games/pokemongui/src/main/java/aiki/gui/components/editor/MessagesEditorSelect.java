package aiki.gui.components.editor;

import aiki.db.*;
import code.sml.util.*;

public final class MessagesEditorSelect {
    public static final String EVO_LEVEL_SIMPLE = "0";
    public static final String EVO_LEVEL_GENDER = "1";
    public static final String EVO_STONE_SIMPLE = "2";
    public static final String EVO_STONE_GENDER = "3";
    public static final String EVO_HAPPINESS = "4";
    public static final String EVO_ITEM = "5";
    public static final String EVO_MOVE = "6";
    public static final String EVO_MOVE_TYPE = "7";
    public static final String EVO_TEAM = "8";
    private MessagesEditorSelect() {
    }
    public static TranslationsFile enGenderRep(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DataBase.DEF_GENDER_REP_FEMALE, "100% Female");
        e_.add(DataBase.DEF_GENDER_REP_MALE, "100% Male");
        e_.add(DataBase.DEF_GENDER_REP_MIXED, "Mixed");
        e_.add(DataBase.DEF_GENDER_REP_LEGENDARY, "Legendary");
        e_.add(DataBase.DEF_GENDER_REP_NO_GENDER, "No gender");
        return e_;
    }
    public static TranslationsFile frGenderRep(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DataBase.DEF_GENDER_REP_FEMALE, "100% Femelle");
        f_.add(DataBase.DEF_GENDER_REP_MALE, "100% Mâle");
        f_.add(DataBase.DEF_GENDER_REP_MIXED, "Mixte");
        f_.add(DataBase.DEF_GENDER_REP_LEGENDARY, "Légendaire");
        f_.add(DataBase.DEF_GENDER_REP_NO_GENDER, "Pas de genre");
        return f_;
    }
    public static TranslationsFile enEvo(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(EVO_LEVEL_SIMPLE, "evolution by level");
        e_.add(EVO_LEVEL_GENDER, "evolution by level and gender");
        e_.add(EVO_STONE_SIMPLE, "evolution by stone");
        e_.add(EVO_STONE_GENDER, "evolution by stone and gender");
        e_.add(EVO_HAPPINESS, "evolution by happiness");
        e_.add(EVO_ITEM, "evolution by worn item");
        e_.add(EVO_MOVE, "evolution by learnt move");
        e_.add(EVO_MOVE_TYPE, "evolution by type of learnt move");
        e_.add(EVO_TEAM, "evolution by member in team");
        return e_;
    }
    public static TranslationsFile frEvo(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(EVO_LEVEL_SIMPLE, "évolution par niveau");
        f_.add(EVO_LEVEL_GENDER, "évolution par niveau et genre");
        f_.add(EVO_STONE_SIMPLE, "évolution par pierre");
        f_.add(EVO_STONE_GENDER, "évolution par pierre et genre");
        f_.add(EVO_HAPPINESS, "évolution par bonheur");
        f_.add(EVO_ITEM, "évolution par objet porté");
        f_.add(EVO_MOVE, "évolution par attaque apprise");
        f_.add(EVO_MOVE_TYPE, "évolution par type d'attaque apprise");
        f_.add(EVO_TEAM, "évolution par membre en équipe");
        return f_;
    }
}
