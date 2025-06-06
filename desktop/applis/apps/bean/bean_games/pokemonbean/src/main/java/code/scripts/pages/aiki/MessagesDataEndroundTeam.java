package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEndroundTeam {
    public static final String M_P_13_EFFECT="effect";
    public static final String M_P_13_OWNER="owner";
    public static final String M_P_13_OWNER_INTRO="owner_intro";
    public static final String M_P_13_TEAM="team";
    public static final String M_P_13_TEAM_INTRO="team_intro";
    private MessagesDataEndroundTeam(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_13_EFFECT,"This effect regards the team of the user.");
        e_.add(M_P_13_OWNER,"The rate that the status of the fighter are deleted is {0}.");
        e_.add(M_P_13_OWNER_INTRO,"The rate that the status of the fighter are deleted:");
        e_.add(M_P_13_TEAM,"The rate that the status of the partners of the fighter are deleted is {0}.");
        e_.add(M_P_13_TEAM_INTRO,"The rate that the status of the partners of the fighter are deleted:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_13_EFFECT,"Cet effet concerne l'équipe du lanceur.");
        f_.add(M_P_13_OWNER,"La probabilité que les statuts du combattant soient supprimés est de {0}.");
        f_.add(M_P_13_OWNER_INTRO,"La probabilité que les statuts du combattant soient supprimés est:");
        f_.add(M_P_13_TEAM,"La probabilité que les statuts des partenaires du combattant soient supprimés est de {0}.");
        f_.add(M_P_13_TEAM_INTRO,"La probabilité que les statuts des partenaires du combattant soient supprimés est de:");
        return f_;
    }
}