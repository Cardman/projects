package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffdamagerate {
    public static final String M_P_46_EFFECT="effect";
    public static final String M_P_46_NEG_RATE="neg_rate";
    public static final String M_P_46_POS_RATE="pos_rate";
    private MessagesDataEffdamagerate(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_46_EFFECT,"This effect makes vary the hp of the user in function by inflicted damage against the target.");
        e_.add(M_P_46_NEG_RATE,"The rate of damage points inflicted against the target lost by the user is {0}.");
        e_.add(M_P_46_POS_RATE,"The rate of damage points inflicted against the target won by the user is {0}.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_46_EFFECT,"Cet effet fait varier les pv du lanceur en fonction des dégâts infligés à la cible.");
        f_.add(M_P_46_NEG_RATE,"Le taux de points de dégâts infligés à la cible perdus par le lanceur est de {0}.");
        f_.add(M_P_46_POS_RATE,"Le taux de points de dégâts infligés à la cible gagnés par le lanceur est de {0}.");
        return f_;
    }
}