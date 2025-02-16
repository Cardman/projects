package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffclone {
    public static final String M_P_40_EFFECT="effect";
    public static final String M_P_40_EFFECT_2="effect_2";
    public static final String M_P_40_EFFECT_3="effect_3";
    public static final String M_P_40_EFFECT_4="effect_4";
    public static final String M_P_40_EFFECT_5="effect_5";
    public static final String M_P_40_EFFECT_6="effect_6";
    public static final String M_P_40_EFFECT_7="effect_7";
    public static final String M_P_40_EFFECT_8="effect_8";
    public static final String M_P_40_EFFECT_9="effect_9";
    private MessagesDataEffclone(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_40_EFFECT,"The user uses {0} of its hp maximum.");
        e_.add(M_P_40_EFFECT_2,"The clone cannot be healed.");
        e_.add(M_P_40_EFFECT_3,"The clone suffers damage instead of its owner except for damage of end of round other by the following moves:");
        e_.add(M_P_40_EFFECT_4,"The used moves by the owner, in the possible case when the clone is forwarded to the sent pokemon suffering damage, are the following:");
        e_.add(M_P_40_EFFECT_5,"The moves that the sent pokemon suffers, in the possible case of the forwarding of clone by used moves by the owner, are the following:");
        e_.add(M_P_40_EFFECT_6,"Damage against a cloned confused pokemon go to the cloned pokemon.");
        e_.add(M_P_40_EFFECT_7,"Recoil damage of a move of a cloned pokemon go to the cloned pokemon.");
        e_.add(M_P_40_EFFECT_8,"The moves based on remaining hp based themselves on remaining hp of the cloned pokemon.");
        e_.add(M_P_40_EFFECT_9,"If the owner of the clone is knocked out, then the clone is destroyed.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_40_EFFECT,"Le lanceur utilise {0} de ses pv max.");
        f_.add(M_P_40_EFFECT_2,"Le clone ne peut pas être soigné.");
        f_.add(M_P_40_EFFECT_3,"Le clone subit les dégâts au lieu de son possesseur sauf pour les dégâts de fin de tour autres que par les attaques suivantes:.");
        f_.add(M_P_40_EFFECT_4,"Les attaques utilisées par le possesseur, dans le cas éventuel où le clone est transmis vers le pokémon entrant subissant des dégâts, sont les suivantes:");
        f_.add(M_P_40_EFFECT_5,"Les attaques que le pokémon entrant subit, dans le cas éventuel du transfert de clone par des attaques utilisées par le possesseur, sont les suivantes:");
        f_.add(M_P_40_EFFECT_6,"Les dégâts d''un pokémon cloné confus vont vers le pokémon cloné.");
        f_.add(M_P_40_EFFECT_7,"Les dégâts de recul d''une attaque d''un pokémon cloné vont vers le pokémon cloné.");
        f_.add(M_P_40_EFFECT_8,"Les attaques se basant sur des pv restants se basent sur les pv restants du pokémon cloné.");
        f_.add(M_P_40_EFFECT_9,"Si le possesseur du clone tombe KO, alors le clone est détruit.");
        return f_;
    }
}