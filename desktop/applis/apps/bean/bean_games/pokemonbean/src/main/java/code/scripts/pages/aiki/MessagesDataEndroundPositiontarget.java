package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEndroundPositiontarget {
    public static final String M_P_9_ANTICIPE="anticipe";
    public static final String M_P_9_EFFECT="effect";
    private MessagesDataEndroundPositiontarget(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_9_ANTICIPE,"The effect of the move happens two rounds after.The move is based on the caracteristics of the target at the moment of using.While the two rounds after using are not finished, none of the following moves can be used.");
        e_.add(M_P_9_EFFECT,"This effect regards the position of the user and the position of the target.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_9_ANTICIPE,"L''effet de l''attaque a lieu deux tours après.L''attaque se base sur les caractéristiques de la cible au moment du lancer.Tant que les deux tours après lancer ne sont pas passés, aucune des attaques suivantes ne peut être utilisée;");
        f_.add(M_P_9_EFFECT,"Cet effet tient compte de la position du lanceur et celle de la cible.");
        return f_;
    }
}