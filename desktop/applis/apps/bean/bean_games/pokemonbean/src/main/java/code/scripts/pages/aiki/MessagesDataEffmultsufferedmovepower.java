package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffmultsufferedmovepower {
    public static final String M_P_51_EFFECT="effect";
    public static final String M_P_51_MULT_POWER="mult_power";
    public static final String M_P_51_MULT_POWER_RATE="mult_power_rate";
    public static final String M_P_51_MULT_POWER_TYPE="mult_power_type";
    private MessagesDataEffmultsufferedmovepower(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_51_EFFECT,"This effect multiplies the power of the suffered moves of the target in function by the type.");
        e_.add(M_P_51_MULT_POWER,"Here is the table of the rates of the power of the moves against the target in function by the type:");
        e_.add(M_P_51_MULT_POWER_RATE,"Rate");
        e_.add(M_P_51_MULT_POWER_TYPE,"Type of move");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_51_EFFECT,"Cet effet multiplie la puissance des attaques subies de la cible en fonction du type.");
        f_.add(M_P_51_MULT_POWER,"Voici le tableau des coefficients des puissances des attaques contre la cible en fonction du type:");
        f_.add(M_P_51_MULT_POWER_RATE,"Coefficient");
        f_.add(M_P_51_MULT_POWER_TYPE,"Type d'attaque");
        return f_;
    }
}