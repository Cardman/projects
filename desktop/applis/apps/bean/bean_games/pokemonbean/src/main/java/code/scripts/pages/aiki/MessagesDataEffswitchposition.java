package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffswitchposition {
    public static final String M_P_64_EFFECT="effect";
    private MessagesDataEffswitchposition(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_64_EFFECT,"The user and the target switch their position at the front of battle.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_64_EFFECT,"Le lanceur et la cible échangent leur position sur le terrain.");
        return f_;
    }
}