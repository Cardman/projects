package code.gui.document;

import code.sml.util.*;

public final class MessagesPkBean {
    public static final String APP_BEAN_FIGHT = "pk_bean_fight";
    private MessagesPkBean() {

    }
    public static TranslationsAppli enFight(){
        TranslationsAppli m = new TranslationsAppli();
        m.getMapping().addEntry(MessagesFightFight.FIGHT,MessagesFightFight.en());
        m.getMapping().addEntry(MessagesFightFighter.FIGHTER,MessagesFightFighter.en());
        m.getMapping().addEntry(MessagesFightTeam.TEAM,MessagesFightTeam.en());
        return m;
    }
    public static TranslationsAppli frFight(){
        TranslationsAppli m = new TranslationsAppli();
        m.getMapping().addEntry(MessagesFightFight.FIGHT,MessagesFightFight.fr());
        m.getMapping().addEntry(MessagesFightFighter.FIGHTER,MessagesFightFighter.fr());
        m.getMapping().addEntry(MessagesFightTeam.TEAM,MessagesFightTeam.fr());
        return m;
    }
}
