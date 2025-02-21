package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEndroundPositionrelation {
    public static final String M_P_8_EFFECT="effect";
    public static final String M_P_8_HEAL_HP="heal_hp";
    private MessagesDataEndroundPositionrelation(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_8_EFFECT,"This effect regards the position of the user and the possible substitute.");
        e_.add(M_P_8_HEAL_HP,"The effect of healing happens one round after at the place of the user at the moment of using.The pokemon that is at the place of the effect wins {0} of its full life.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_8_EFFECT,"Cet effet tient compte de la position du lanceur et le remplacant éventuel.");
        f_.add(M_P_8_HEAL_HP,"L''effet de soin a lieu un tour après à l''emplacement du lanceur au moment du lancer.Le pokemon qui est placé à l''emplacement de l''effet gagne {0} de sa vie totale.");
        return f_;
    }
}