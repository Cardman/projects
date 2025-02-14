package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffteamwhilesendingfoe {
    public static final String M_P_67_BOOST="boost";
    public static final String M_P_67_DAMAGE_RATE_AGAINST_FOE="damage_rate_against_foe";
    public static final String M_P_67_DELETE_STATUS_IF_TYPES="delete_status_if_types";
    public static final String M_P_67_EFFECT="effect";
    public static final String M_P_67_FORMULA="formula";
    public static final String M_P_67_NB_USES="nb_uses";
    public static final String M_P_67_REASONS_SENDING="reasons_sending";
    public static final String M_P_67_STATISTIC="statistic";
    public static final String M_P_67_STATISTICS="statistics";
    public static final String M_P_67_STATUS="status";
    public static final String M_P_67_STATUS_IF_NB="status_if_nb";
    private MessagesDataEffteamwhilesendingfoe(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_67_BOOST,"Variation of the level");
        e_.add(M_P_67_DAMAGE_RATE_AGAINST_FOE,"The formula of damage rate inflicted against the of is:{0}");
        e_.add(M_P_67_DELETE_STATUS_IF_TYPES,"The effect is deleted if the sent foe owns one of the following types:");
        e_.add(M_P_67_EFFECT,"This effect happens while sending a foe at the front battle.");
        e_.add(M_P_67_FORMULA,"{0}");
        e_.add(M_P_67_NB_USES,"Number of uses by the team");
        e_.add(M_P_67_REASONS_SENDING,"While a foe is sent at the front battle, the effect fails if and only if one of the conditions is checked:");
        e_.add(M_P_67_STATISTIC,"Statistic");
        e_.add(M_P_67_STATISTICS,"Here is the variations of the levels of the statistics of the sent foe fighter:");
        e_.add(M_P_67_STATUS,"Status");
        e_.add(M_P_67_STATUS_IF_NB,"Here is the table of the status that the sent foe can have in function by the number of uses of the move by the team:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_67_BOOST,"Variation du boost");
        f_.add(M_P_67_DAMAGE_RATE_AGAINST_FOE,"La formule du taux de dégâts infligés à l''adversaire vaut:{0}");
        f_.add(M_P_67_DELETE_STATUS_IF_TYPES,"L''effet est supprimé si l''adversaire entrant possède un des types suivants:");
        f_.add(M_P_67_EFFECT,"Cet effet a lieu lors de l''entrée d''un adversaire sur le terrain.");
        f_.add(M_P_67_FORMULA,"{0}");
        f_.add(M_P_67_NB_USES,"Nombre d''utilisations par l''équipe");
        f_.add(M_P_67_REASONS_SENDING,"Lorsqu''un adversaire entre sur le terrain, l''effet échoue si et seulement si une des conditions est vérifiée:");
        f_.add(M_P_67_STATISTIC,"Statistique");
        f_.add(M_P_67_STATISTICS,"Voici les variations des boosts des statistiques du combattant adverse entrant:");
        f_.add(M_P_67_STATUS,"Statut");
        f_.add(M_P_67_STATUS_IF_NB,"Voici le tableau des statuts que l''adversaire entrant peut prendre en fonction du nombre d''utilisations de l''attaque par l''équipe:");
        return f_;
    }
}