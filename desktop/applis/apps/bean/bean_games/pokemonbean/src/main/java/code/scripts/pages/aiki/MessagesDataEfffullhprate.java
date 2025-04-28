package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEfffullhprate {
    public static final String M_P_48_CLOSEST_FOE_DAMAGE_RATE_HP="closest_foe_damage_rate_hp";
    public static final String M_P_48_CLOSEST_FOE_DAMAGE_RATE_HP_INTRO="closest_foe_damage_rate_hp_intro";
    public static final String M_P_48_EFFECT="effect";
    public static final String M_P_48_FORMULA="formula";
    public static final String M_P_48_LEFT_USER_HP="left_user_hp";
    public static final String M_P_48_LEFT_USER_HP_INTRO="left_user_hp_intro";
    public static final String M_P_48_RESTORED="restored";
    public static final String M_P_48_RESTORED_INTRO="restored_intro";
    private MessagesDataEfffullhprate(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_48_CLOSEST_FOE_DAMAGE_RATE_HP,"Each foe fighter close to the target looses {0} of its full hp.");
        e_.add(M_P_48_CLOSEST_FOE_DAMAGE_RATE_HP_INTRO,"Rate of loss of full hp of each foe fighter close to the target:");
        e_.add(M_P_48_EFFECT,"This effect makes vary the hp in function by a rate of full hp of the fighter.");
        e_.add(M_P_48_FORMULA,"{0}");
        e_.add(M_P_48_LEFT_USER_HP,"The user decreases {0} of its full hp.");
        e_.add(M_P_48_LEFT_USER_HP_INTRO,"Decreasing rate of full hp of the user:");
        e_.add(M_P_48_RESTORED,"Here is the rate of full restored hp of the user:{0}");
        e_.add(M_P_48_RESTORED_INTRO,"Here is the rate of full restored hp of the user:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_48_CLOSEST_FOE_DAMAGE_RATE_HP,"Chaque combattant adverse adjacent à la cible perd {0} de ses pv max.");
        f_.add(M_P_48_CLOSEST_FOE_DAMAGE_RATE_HP_INTRO,"Taux de perte des pv max de chaque combattant adverse adjacent à la cible:");
        f_.add(M_P_48_EFFECT,"Cet effet fait varier les pv en fonction d'un quota de pv totaux du combattant.");
        f_.add(M_P_48_FORMULA,"{0}");
        f_.add(M_P_48_LEFT_USER_HP,"Le lanceur sacrifie {0} de ses pv max.");
        f_.add(M_P_48_LEFT_USER_HP_INTRO,"Taux de baisse des pv max du lanceur:");
        f_.add(M_P_48_RESTORED,"Voici le taux des pv max restaurés du lanceur:{0}");
        f_.add(M_P_48_RESTORED_INTRO,"Voici le taux des pv max restaurés du lanceur:");
        return f_;
    }
}