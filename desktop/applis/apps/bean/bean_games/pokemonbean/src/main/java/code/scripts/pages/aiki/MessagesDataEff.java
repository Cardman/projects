package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEff {
    public static final String M_P_36_FORMULA="formula";
    public static final String M_P_36_NEED_SUCESS="need_sucess";
    public static final String M_P_36_REASONS="reasons";
    public static final String M_P_36_TARGETS="targets";
    public static final String M_P_36_TARGETS_ADJ_ADV="targets_ADJ_ADV";
    public static final String M_P_36_TARGETS_ADJ_MULT="targets_ADJ_MULT";
    public static final String M_P_36_TARGETS_ADJ_UNIQ="targets_ADJ_UNIQ";
    public static final String M_P_36_TARGETS_ALLIE="targets_ALLIE";
    public static final String M_P_36_TARGETS_ALLIES="targets_ALLIES";
    public static final String M_P_36_TARGETS_ANY_FOE="targets_ANY_FOE";
    public static final String M_P_36_TARGETS_AUTRE_UNIQ="targets_AUTRE_UNIQ";
    public static final String M_P_36_TARGETS_GLOBALE="targets_GLOBALE";
    public static final String M_P_36_TARGETS_LANCEUR="targets_LANCEUR";
    public static final String M_P_36_TARGETS_PSEUDO_GLOBALE="targets_PSEUDO_GLOBALE";
    public static final String M_P_36_TARGETS_TOUS_ADV="targets_TOUS_ADV";
    public static final String M_P_36_TARGETS_UNIQUE_IMPORTE="targets_UNIQUE_IMPORTE";
    private MessagesDataEff(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_36_FORMULA,"{0}");
        e_.add(M_P_36_NEED_SUCESS,"The effect need the success of the first effect.");
        e_.add(M_P_36_REASONS,"The effect fails is and only if one of the conditions is checked:");
        e_.add(M_P_36_TARGETS,"The set of targets of the current effect is the following one:");
        e_.add(M_P_36_TARGETS_ADJ_ADV,"all closest foes to the user");
        e_.add(M_P_36_TARGETS_ADJ_MULT,"all closest fighters to the user");
        e_.add(M_P_36_TARGETS_ADJ_UNIQ,"a close fighter to the user");
        e_.add(M_P_36_TARGETS_ALLIE,"a partner of the user");
        e_.add(M_P_36_TARGETS_ALLIES,"the members of the team of the user");
        e_.add(M_P_36_TARGETS_ANY_FOE,"a foe");
        e_.add(M_P_36_TARGETS_AUTRE_UNIQ,"a fighter other than the user");
        e_.add(M_P_36_TARGETS_GLOBALE,"all fighters");
        e_.add(M_P_36_TARGETS_LANCEUR,"the user");
        e_.add(M_P_36_TARGETS_PSEUDO_GLOBALE,"fighters other than the user");
        e_.add(M_P_36_TARGETS_TOUS_ADV,"all foes");
        e_.add(M_P_36_TARGETS_UNIQUE_IMPORTE,"a fighter be possibly the user or not");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_36_FORMULA,"{0}");
        f_.add(M_P_36_NEED_SUCESS,"L''effet nécessite la réussite de l''effet primaire.");
        f_.add(M_P_36_REASONS,"L''effet échoue si et seulement une des conditions est vérifiée:");
        f_.add(M_P_36_TARGETS,"L''ensemble des cibles de l''effet courant est le suivant:");
        f_.add(M_P_36_TARGETS_ADJ_ADV,"tous les adversaires adjacent au lanceur");
        f_.add(M_P_36_TARGETS_ADJ_MULT,"tous les combattants adjacent au lanceur");
        f_.add(M_P_36_TARGETS_ADJ_UNIQ,"un combattant adjacent au lanceur");
        f_.add(M_P_36_TARGETS_ALLIE,"un partenaire du lanceur");
        f_.add(M_P_36_TARGETS_ALLIES,"les membres de l''équipe du lanceur");
        f_.add(M_P_36_TARGETS_ANY_FOE,"un adversaire");
        f_.add(M_P_36_TARGETS_AUTRE_UNIQ,"un combattant autre que le lanceur");
        f_.add(M_P_36_TARGETS_GLOBALE,"les combattants y compris le lanceur");
        f_.add(M_P_36_TARGETS_LANCEUR,"le lanceur");
        f_.add(M_P_36_TARGETS_PSEUDO_GLOBALE,"les combattants autre que le lanceur");
        f_.add(M_P_36_TARGETS_TOUS_ADV,"tous les adversaires");
        f_.add(M_P_36_TARGETS_UNIQUE_IMPORTE,"un combattant pouvant être le lanceur ou non");
        return f_;
    }
}