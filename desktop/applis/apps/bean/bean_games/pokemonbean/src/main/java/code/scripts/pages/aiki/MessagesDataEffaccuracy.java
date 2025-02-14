package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffaccuracy {
    public static final String M_P_37_ACCURACY_MAX="accuracy_max";
    public static final String M_P_37_EFFECT="effect";
    private MessagesDataEffaccuracy(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_37_ACCURACY_MAX,"The accuracy of the next used move against the target is 100%.");
        e_.add(M_P_37_EFFECT,"This move lets to achieve targets disappearing during the next round this effect.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_37_ACCURACY_MAX,"La précision de la prochaine attaque utilisée sur la cible vaut 100%.");
        f_.add(M_P_37_EFFECT,"Cette attaque permet de toucher des cibles disparaissant pendant le tour suivant cet effet.");
        return f_;
    }
}