package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEfforder {
    public static final String M_P_53_AFTER_USER="after_user";
    public static final String M_P_53_EFFECT="effect";
    public static final String M_P_53_LAST="last";
    private MessagesDataEfforder(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_53_AFTER_USER,"If the target is not KO, the target uses a move after the user.");
        e_.add(M_P_53_EFFECT,"This effect changes the order of the fighters.");
        e_.add(M_P_53_LAST,"If the user is not knocked out after the use of its move, the target lastly uses a move.If it exists some pokemon targetted by this move, they will use a move in the same order than the other one.If the user of this move is knocked out before the target using a move and if the target is not KO, then the effect is stopped.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_53_AFTER_USER,"Si la cible n'est pas KO, la cible attaque après le lanceur.");
        f_.add(M_P_53_EFFECT,"Cet effet change l'ordre d'attaque des combattants.");
        f_.add(M_P_53_LAST,"Si le lanceur ne tombe pas KO après son attaque, la cible attaque en dernier.S'il existe plusieurs pokémons cibles de cette attaque, il attaqueront dans le même ordre que les autres.Si le lanceur de cette attaque tombe KO avant l'attaque de la cible et si la cible n'est pas KO, alors l'effet est arrêté.");
        return f_;
    }
}