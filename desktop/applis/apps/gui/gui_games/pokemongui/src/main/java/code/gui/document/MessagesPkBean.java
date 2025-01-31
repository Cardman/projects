package code.gui.document;

import code.sml.util.*;

public final class MessagesPkBean {
    public static final String APP_BEAN = "pk_bean";
    public static final String APP_BEAN_FIGHT = "pk_bean_fight";
    public static final String FIGHT = "fight";
    public static final String FIGHTER = "fighter";
    public static final String TEAM = "team";
    public static final String POKEMON = "pokemon";
    public static final String GAMEPROG = "gameprog";
    public static final String DIFFICULTY = "difficulty";
    private MessagesPkBean() {

    }
    public static TranslationsAppli en(){
        TranslationsAppli m = new TranslationsAppli();
        m.getMapping().addEntry(DIFFICULTY,MessagesGameDifficulty.en());
        m.getMapping().addEntry(GAMEPROG,MessagesProgGameprog.en());
        m.getMapping().addEntry(POKEMON,MessagesPkPokemon.en());
        return m;
    }
    public static TranslationsAppli fr(){
        TranslationsAppli m = new TranslationsAppli();
        m.getMapping().addEntry(DIFFICULTY,MessagesGameDifficulty.fr());
        m.getMapping().addEntry(GAMEPROG,MessagesProgGameprog.fr());
        m.getMapping().addEntry(POKEMON,MessagesPkPokemon.fr());
        return m;
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
