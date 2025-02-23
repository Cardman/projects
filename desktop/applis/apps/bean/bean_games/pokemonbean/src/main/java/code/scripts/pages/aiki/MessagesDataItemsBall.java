package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataItemsBall {
    public static final String M_P_16_FORMULA="formula";
    public static final String M_P_16_RATE_CATCHING="rate_catching";
    private MessagesDataItemsBall(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_16_FORMULA,"{0}");
        e_.add(M_P_16_RATE_CATCHING,"The formula of the rate catching is the following one:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_16_FORMULA,"{0}");
        f_.add(M_P_16_RATE_CATCHING,"La formule du taux de capture est la suivante:");
        return f_;
    }
}