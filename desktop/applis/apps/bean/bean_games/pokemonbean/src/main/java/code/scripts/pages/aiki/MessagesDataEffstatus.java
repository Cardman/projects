package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffstatus {
    public static final String M_P_59_DELETED_STATUS="deleted_status";
    public static final String M_P_59_EFFECT="effect";
    public static final String M_P_59_FAIL="fail";
    public static final String M_P_59_FORMULA="formula";
    public static final String M_P_59_FORWARD="forward";
    public static final String M_P_59_KO_USER="ko_user";
    public static final String M_P_59_LAW_STATUS="law_status";
    public static final String M_P_59_OTHER_STATUS="other_status";
    public static final String M_P_59_RATE_EVENT="rate_event";
    public static final String M_P_59_STATUS="status";
    private MessagesDataEffstatus(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_59_DELETED_STATUS,"The deleted status of the target are the following one:");
        e_.add(M_P_59_EFFECT,"This effect adds/deletes a status at least.");
        e_.add(M_P_59_FAIL,"Sufficient conditions of fail");
        e_.add(M_P_59_FORMULA,"{0}");
        e_.add(M_P_59_FORWARD,"The status of the user are forwarded to the target that does not have.");
        e_.add(M_P_59_KO_USER,"The user is knocked out. The substitute of the user is full healed (pp, hp, status).");
        e_.add(M_P_59_LAW_STATUS,"Here is the law of editing status to add to the target:");
        e_.add(M_P_59_OTHER_STATUS,"No added status");
        e_.add(M_P_59_RATE_EVENT,"Rate");
        e_.add(M_P_59_STATUS,"Status");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_59_DELETED_STATUS,"Les statuts de la cible supprimés sont les suivants:");
        f_.add(M_P_59_EFFECT,"Cet effet ajoute/supprime au moins un statut.");
        f_.add(M_P_59_FAIL,"Conditions suffisantes d''échec");
        f_.add(M_P_59_FORMULA,"{0}");
        f_.add(M_P_59_FORWARD,"Les statuts du lanceur sont transférés vers la cible qu''elle n''a pas.");
        f_.add(M_P_59_KO_USER,"Le lanceur tombe KO. Le remplaçant du lanceur est totalement soigné (pp, pv, statuts).");
        f_.add(M_P_59_LAW_STATUS,"Voici la loi de tirage des statuts à ajouter à la cible:");
        f_.add(M_P_59_OTHER_STATUS,"Aucun statut supplémentaire");
        f_.add(M_P_59_RATE_EVENT,"Probabilité");
        f_.add(M_P_59_STATUS,"Statut");
        return f_;
    }
}