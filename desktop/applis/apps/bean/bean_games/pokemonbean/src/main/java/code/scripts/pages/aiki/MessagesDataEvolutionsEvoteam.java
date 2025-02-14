package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEvolutionsEvoteam {
    public static final String M_P_80_TEAM="team";
    private MessagesDataEvolutionsEvoteam(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_80_TEAM,"The following pokemon must be present in the team while {0} grows a level:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_80_TEAM,"Le pokemon suivant doit être présent dans l''équipe pendant que {0} monte d''un niveau:");
        return f_;
    }
}
