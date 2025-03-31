package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffprotection {
    public static final String M_P_55_EFFECT="effect";
    public static final String M_P_55_PROT_MULTI_TARGETS="prot_multi_targets";
    public static final String M_P_55_PROT_PRIO="prot_prio";
    public static final String M_P_55_PROT_SINGLE="prot_single";
    public static final String M_P_55_PROT_SINGLE_DAMAGE="prot_single_damage";
    public static final String M_P_55_PROT_SINGLE_KO="prot_single_ko";
    public static final String M_P_55_PROT_SINGLE_STATUS="prot_single_status";
    private MessagesDataEffprotection(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_55_EFFECT,"This effect protects a part of the team of the user.");
        e_.add(M_P_55_PROT_MULTI_TARGETS,"The team of the user is protected against multiple targets moves.");
        e_.add(M_P_55_PROT_PRIO,"The team of the user is protected against moves with priority greater than 0.");
        e_.add(M_P_55_PROT_SINGLE,"The user is protected against moves that target the user.");
        e_.add(M_P_55_PROT_SINGLE_DAMAGE,"The user is protected against damaging moves that target the user.");
        e_.add(M_P_55_PROT_SINGLE_KO,"The user cannot be knocked out by moves that target the user.The user keeps {0} hp at leeast.");
        e_.add(M_P_55_PROT_SINGLE_STATUS,"The user is protected against not damaging moves that target the user.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_55_EFFECT,"Cet effet protège une partie de l'équipe du lanceur.");
        f_.add(M_P_55_PROT_MULTI_TARGETS,"L'équipe du lanceur est protégée des attaques multi cibles.");
        f_.add(M_P_55_PROT_PRIO,"L'équipe du lanceur est protégée des attaques de priorité supérieure à 0.");
        f_.add(M_P_55_PROT_SINGLE,"Le lanceur est protégée des attaques où il est pris pour cible.");
        f_.add(M_P_55_PROT_SINGLE_DAMAGE,"Le lanceur est protégée des attaques offensives où il est pris pour cible.");
        f_.add(M_P_55_PROT_SINGLE_KO,"Le lanceur ne peut pas tomber KO par des attaques où il est pris pour cible.Il garde au moins {0} pv.");
        f_.add(M_P_55_PROT_SINGLE_STATUS,"Le lanceur est protégée des attaques non offensives où il est pris pour cible.");
        return f_;
    }
}