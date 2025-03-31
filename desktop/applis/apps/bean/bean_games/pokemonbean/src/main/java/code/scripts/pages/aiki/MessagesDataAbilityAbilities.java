package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataAbilityAbilities {
    public static final String M_P_0_ABILITIES="abilities";
    public static final String M_P_0_CONTENT="content";
    public static final String M_P_0_INDEX="index";
    public static final String M_P_0_TITLE="title";
    public static final String M_P_0_OK="ok";
    private MessagesDataAbilityAbilities(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_0_ABILITIES,"Abilities");
        e_.add(M_P_0_CONTENT,"Content of the name of ability:");
        e_.add(M_P_0_INDEX,"Return to the index");
        e_.add(M_P_0_TITLE,"Search abilities");
        e_.add(M_P_0_OK,"OK");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_0_ABILITIES,"Capacités");
        f_.add(M_P_0_CONTENT,"Contenu du nom de capacité:");
        f_.add(M_P_0_INDEX,"Revenir à l'indexe");
        f_.add(M_P_0_TITLE,"Recherche de capacités");
        f_.add(M_P_0_OK,"OK");
        return f_;
    }
}