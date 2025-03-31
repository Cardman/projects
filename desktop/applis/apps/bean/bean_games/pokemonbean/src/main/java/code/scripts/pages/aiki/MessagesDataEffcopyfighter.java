package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffcopyfighter {
    public static final String M_P_42_EFFECT="effect";
    public static final String M_P_42_PP_MOVES="pp_moves";
    private MessagesDataEffcopyfighter(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_42_EFFECT,"The user copies the data of the target: name, height, weight, ability, level, types, base statistics without hp, moves.");
        e_.add(M_P_42_PP_MOVES,"The number of pp by copied move is {0}.The moves supposed definitively learnt by a copying move are only temporarily learnt.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_42_EFFECT,"Le lanceur copie l'espèce de la cible: nom, taille, masse, capacité, niveau, types, statistiques de base sans les pv, attaques.");
        f_.add(M_P_42_PP_MOVES,"Le nombre de pp par attaque copiée est de {0}.Les attaques soit disant définitivement apprises par une attaque de copie ne sont alors que temporairement apprises.");
        return f_;
    }
}