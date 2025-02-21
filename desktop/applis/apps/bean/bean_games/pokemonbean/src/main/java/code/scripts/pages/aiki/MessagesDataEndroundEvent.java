package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEndroundEvent {
    public static final String M_P_4_RANK="rank";
    private MessagesDataEndroundEvent(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_4_RANK,"The rank of the effect is {0}.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_4_RANK,"Le rang de l''effet est de {0}.");
        return f_;
    }
}