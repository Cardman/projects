package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffunprotectfromtypes {
    public static final String M_P_68_ATTACK_TARGET_TYPES="attack_target_types";
    public static final String M_P_68_DISABLE_IMMU_FROM_MOVES="disable_immu_from_moves";
    public static final String M_P_68_DISABLE_IMMU_TYPES="disable_immu_types";
    public static final String M_P_68_EFFECT="effect";
    public static final String M_P_68_TYPES="types";
    public static final String M_P_68_TYPES_DAMAG="types_damag";
    public static final String M_P_68_TYPES_PK="types_pk";
    private MessagesDataEffunprotectfromtypes(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_68_ATTACK_TARGET_TYPES,"The target can be affected by moves with one of the following types:");
        e_.add(M_P_68_DISABLE_IMMU_FROM_MOVES,"The moves, whose effects protecting the target against a type are stopped, are the following one:");
        e_.add(M_P_68_DISABLE_IMMU_TYPES,"The target is not protected anymore against the moves aux attaques with one of the following types:");
        e_.add(M_P_68_EFFECT,"By disabling some protections of the target, the effect allows the user to use moves that the target was protected.");
        e_.add(M_P_68_TYPES,"Here is the list of zero duals damaging type - defending type affected to 1:");
        e_.add(M_P_68_TYPES_DAMAG,"Type of the move");
        e_.add(M_P_68_TYPES_PK,"Type of the targetted pokemon");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_68_ATTACK_TARGET_TYPES,"La cible peut être affectée par des attaques d'un des types suivants:");
        f_.add(M_P_68_DISABLE_IMMU_FROM_MOVES,"Les attaques dont les effets immunisant la cible à un type sont stoppés sont les suivantes:");
        f_.add(M_P_68_DISABLE_IMMU_TYPES,"La cible n'est plus immunisées aux attaques d'un des types suivants:");
        f_.add(M_P_68_EFFECT,"En désactivant certaines immunités de la cible, l'effet permet au lanceur d'utiliser des attaques auxquelles la cible était immunisée.");
        f_.add(M_P_68_TYPES,"Voici la liste des couples type offensif - type défensif nuls passant à 1:");
        f_.add(M_P_68_TYPES_DAMAG,"Type de l'attaque");
        f_.add(M_P_68_TYPES_PK,"Type du pokemon attaqué");
        return f_;
    }
}