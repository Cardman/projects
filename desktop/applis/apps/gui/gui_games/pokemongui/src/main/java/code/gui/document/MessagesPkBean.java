package code.gui.document;

import code.sml.util.*;

public final class MessagesPkBean {
    public static final String APP_BEAN_FIGHT = "pk_bean_fight";
    public static final String FIGHT = "fight";
    public static final String FIGHTER = "fighter";
    public static final String TEAM = "team";

    private MessagesPkBean() {

    }
    public static TranslationsAppli enFight(){
        TranslationsAppli m = new TranslationsAppli();
        m.getMapping().addEntry(FIGHT,MessagesFightFight.en());
        m.getMapping().addEntry(FIGHTER,MessagesFightFighter.en());
        m.getMapping().addEntry(TEAM,MessagesFightTeam.en());
        return m;
    }
    public static TranslationsAppli frFight(){
        TranslationsAppli m = new TranslationsAppli();
        m.getMapping().addEntry(FIGHT,MessagesFightFight.fr());
        m.getMapping().addEntry(FIGHTER,MessagesFightFighter.fr());
        m.getMapping().addEntry(TEAM,MessagesFightTeam.fr());
        return m;
    }
}
