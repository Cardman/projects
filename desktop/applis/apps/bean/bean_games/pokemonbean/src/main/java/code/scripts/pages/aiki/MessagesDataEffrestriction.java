package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffrestriction {
    public static final String M_P_57_EFFECT_ITEM="effect_item";
    public static final String M_P_57_EFFECT_ITEM_2="effect_item_2";
    public static final String M_P_57_EFFECT_MOVE="effect_move";
    public static final String M_P_57_FORBID_LAST_MOVE="forbid_last_move";
    public static final String M_P_57_FORBID_STATUS_MOVE="forbid_status_move";
    public static final String M_P_57_FORBID_USER_MOVES="forbid_user_moves";
    public static final String M_P_57_FORBID_USE_LAST_MOVE="forbid_use_last_move";
    public static final String M_P_57_FORCE_USE_LAST_MOVE="force_use_last_move";
    private MessagesDataEffrestriction(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_57_EFFECT_ITEM,"This effect disables the items of its targets.");
        e_.add(M_P_57_EFFECT_ITEM_2,"The item of the fighter has not effect anymore during some rounds.");
        e_.add(M_P_57_EFFECT_MOVE,"This effect forbids its targets to use some moves.");
        e_.add(M_P_57_FORBID_LAST_MOVE,"The fighter cannot choose consecutively the same move. But, the fighter keeps the possibility to use one round over two.");
        e_.add(M_P_57_FORBID_STATUS_MOVE,"The fighter cannot use not damaging moves anymore.");
        e_.add(M_P_57_FORBID_USER_MOVES,"The target cannot use the moves that the user owns.");
        e_.add(M_P_57_FORBID_USE_LAST_MOVE,"The fighter cannot reuse the move having just been chosen during some rounds.");
        e_.add(M_P_57_FORCE_USE_LAST_MOVE,"The fighter must reuse the move having just been chosen during some rounds.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_57_EFFECT_ITEM,"Cette effet désactive les objets de ses cibles.");
        f_.add(M_P_57_EFFECT_ITEM_2,"L''objet du combattant n''a plus d''effet pendant un certain nombre de tour.");
        f_.add(M_P_57_EFFECT_MOVE,"Cette effet interdit à ses cibles d''utiliser certaines attaques.");
        f_.add(M_P_57_FORBID_LAST_MOVE,"Le combattant ne peut pas choisir consécutivement la même attaque. Mais, il garde la possibilité d''utiliser un tour sur deux.");
        f_.add(M_P_57_FORBID_STATUS_MOVE,"Le combattant ne peut plus utiliser les attaques non offensives.");
        f_.add(M_P_57_FORBID_USER_MOVES,"La cible ne peut pas utiliser les attaques que le lanceur possède.");
        f_.add(M_P_57_FORBID_USE_LAST_MOVE,"Le combattant ne peut pas réutiliser l''attaque venant d''être choisie pendant un certain nombre de tour.");
        f_.add(M_P_57_FORCE_USE_LAST_MOVE,"Le combattant doit réutiliser l''attaque venant d''être choisie pendant un certain nombre de tour.");
        return f_;
    }
}