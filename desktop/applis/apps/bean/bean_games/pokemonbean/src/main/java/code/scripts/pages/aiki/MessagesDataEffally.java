package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffally {
    public static final String M_P_38_EFFECT="effect";
    public static final String M_P_38_MUL_ALLY_DAMAGE="mul_ally_damage";
    private MessagesDataEffally(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_38_EFFECT,"The effect lets the partner to get bonuses.");
        e_.add(M_P_38_MUL_ALLY_DAMAGE,"The move lets the partner to multiply damage that the partner by {0}.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_38_EFFECT,"L'effet permet au partenaire d'obtenir des bonus.");
        f_.add(M_P_38_MUL_ALLY_DAMAGE,"L''attaque permet au partenaire de multiplier les dégâts qu''il inflige par {0}.");
        return f_;
    }
}