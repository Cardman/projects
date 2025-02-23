package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataMapPokemonKey {
    public static final String M_P_34_ABILITY="ability";
    public static final String M_P_34_ALLY_TEAM="ally_team";
    public static final String M_P_34_FOE_TEAM="foe_team";
    public static final String M_P_34_GENDER="gender";
    public static final String M_P_34_INDEX="index";
    public static final String M_P_34_ITEM="item";
    public static final String M_P_34_ITEM_NO="item_no";
    public static final String M_P_34_LEVEL="level";
    public static final String M_P_34_MAP="map";
    public static final String M_P_34_MOVES="moves";
    public static final String M_P_34_MULTIPLICITY="multiplicity";
    public static final String M_P_34_NAME="name";
    public static final String M_P_34_REWARD="reward";
    private MessagesDataMapPokemonKey(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_34_ABILITY,"Ability:");
        e_.add(M_P_34_ALLY_TEAM,"Pokemon of the ally of the player:");
        e_.add(M_P_34_FOE_TEAM,"Pokemon of the foe trainer:");
        e_.add(M_P_34_GENDER,"Gender:");
        e_.add(M_P_34_INDEX,"Return to the index");
        e_.add(M_P_34_ITEM,"Item:");
        e_.add(M_P_34_ITEM_NO,"No item");
        e_.add(M_P_34_LEVEL,"Level:");
        e_.add(M_P_34_MAP,"Return to the set of places");
        e_.add(M_P_34_MOVES,"Moves");
        e_.add(M_P_34_MULTIPLICITY,"Multiplicity:");
        e_.add(M_P_34_NAME,"Name:");
        e_.add(M_P_34_REWARD,"Base reward.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_34_ABILITY,"Capacité:");
        f_.add(M_P_34_ALLY_TEAM,"Les pokemon de l''allié du joueur:");
        f_.add(M_P_34_FOE_TEAM,"Les pokemon du dresseur ennemi:");
        f_.add(M_P_34_GENDER,"Genre:");
        f_.add(M_P_34_INDEX,"Revenir à l''indexe");
        f_.add(M_P_34_ITEM,"Objet:");
        f_.add(M_P_34_ITEM_NO,"Aucun objet");
        f_.add(M_P_34_LEVEL,"Niveau:");
        f_.add(M_P_34_MAP,"Revenir à l''ensemble des lieux");
        f_.add(M_P_34_MOVES,"Attaques");
        f_.add(M_P_34_MULTIPLICITY,"Multiplicité:");
        f_.add(M_P_34_NAME,"Nom:");
        f_.add(M_P_34_REWARD,"Récompense de base");
        return f_;
    }
}