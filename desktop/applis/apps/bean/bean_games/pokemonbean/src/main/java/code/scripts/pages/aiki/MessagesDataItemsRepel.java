package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataItemsRepel {
    public static final String M_P_30_STEPS="steps";
    private MessagesDataItemsRepel(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_30_STEPS,"The wild pokemon are repelled during {0} steps.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_30_STEPS,"Les pokémons sauvages sont repoussés pendant {0} pas.");
        return f_;
    }
}