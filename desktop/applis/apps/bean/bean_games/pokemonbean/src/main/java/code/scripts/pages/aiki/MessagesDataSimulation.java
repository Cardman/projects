package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataSimulation {
    public static final String M_P_86_FREE_TEAMS = "free_teams";
    public static final String M_P_86_NEXT_BUTTON = "next_button";
    private MessagesDataSimulation() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_86_FREE_TEAMS,"Free teams");
        e_.add(M_P_86_NEXT_BUTTON,">>");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_86_FREE_TEAMS,"Équipes libres");
        f_.add(M_P_86_NEXT_BUTTON,">>");
        return f_;
    }
}
