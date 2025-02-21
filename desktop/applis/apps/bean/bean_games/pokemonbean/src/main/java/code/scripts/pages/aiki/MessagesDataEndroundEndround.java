package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEndroundEndround {
    public static final String M_P_3_TITLE="title";
    public static final String M_P_3_INDEX="index";
    private MessagesDataEndroundEndround(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_3_TITLE,"End of round");
        e_.add(M_P_3_INDEX,"Return to the index");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_3_TITLE,"Fin de tour");
        f_.add(M_P_3_INDEX,"Revenir à l''indexe");
        return f_;
    }
}