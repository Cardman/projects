package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesPkBean {
    public static final String APP_BEAN_DATA = "pk_bean_data";
    public static final String APP_BEAN = "pk_bean";
    public static final String APP_BEAN_FIGHT = "pk_bean_fight";
    public static final String FIGHT = "fight";
    public static final String FIGHTER = "fighter";
    public static final String INDEX = "index";
    public static final String POKEDEX = "pokemon/pokedex";
    public static final String TEAM = "team";
    public static final String POKEMON = "pokemon";
    public static final String GAMEPROG = "gameprog";
    public static final String DIFFICULTY = "difficulty";
    public static final String SIMULATION = "simulation/simulation";
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
    public static TranslationsAppli enData(){
        TranslationsAppli m = new TranslationsAppli();
        m.getMapping().addEntry(INDEX,MessagesDataIndex.en());
        m.getMapping().addEntry(POKEDEX,MessagesDataPokemonPokedex.en());
        return m;
    }
    public static TranslationsAppli frData(){
        TranslationsAppli m = new TranslationsAppli();
        m.getMapping().addEntry(INDEX,MessagesDataIndex.fr());
        m.getMapping().addEntry(POKEDEX,MessagesDataPokemonPokedex.fr());
        return m;
    }
}
