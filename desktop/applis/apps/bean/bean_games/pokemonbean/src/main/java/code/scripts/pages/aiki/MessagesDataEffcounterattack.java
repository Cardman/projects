package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffcounterattack {
    public static final String M_P_44_COUNTER_PROTECT="counter_protect";
    public static final String M_P_44_DROPPED_STAT="dropped_stat";
    public static final String M_P_44_DROPPED_STAT_S="dropped_stat_s";
    public static final String M_P_44_DROPPED_STAT_V="dropped_stat_v";
    public static final String M_P_44_EFFECT="effect";
    public static final String M_P_44_FAIL_PROTECT="fail_protect";
    public static final String M_P_44_FORMULA="formula";
    public static final String M_P_44_SUFFERING_DIRECT="suffering_direct";
    public static final String M_P_44_SUFFERING_TYPES="suffering_types";
    public static final String M_P_44_SUFFERING_TYPES_HP="suffering_types_hp";
    public static final String M_P_44_SUFFERING_TYPES_T="suffering_types_t";
    private MessagesDataEffcounterattack(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_44_COUNTER_PROTECT,"While a pokemon uses a move against the user of the move {0}, the counter attack of the effect fails if and only if one of the conditions is checked:");
        e_.add(M_P_44_DROPPED_STAT,"While a pokemon uses a move against the user of the move {0} with a direct move, the levels of its statistics vary by the following way:");
        e_.add(M_P_44_DROPPED_STAT_S,"Statistic");
        e_.add(M_P_44_DROPPED_STAT_V,"Variation of the statistic");
        e_.add(M_P_44_EFFECT,"The effect protects its user against some moves and can let its user counter attack (return some effects of these moves).");
        e_.add(M_P_44_FAIL_PROTECT,"While a pokemon uses a move against the user of the move {0}, the protection of the effect fails if and only if one of the conditions is checked:");
        e_.add(M_P_44_FORMULA,"{0}");
        e_.add(M_P_44_SUFFERING_DIRECT,"While a pokemon uses a move against the user of the move {0} with a direct move, the rate of lost life is {1}.");
        e_.add(M_P_44_SUFFERING_TYPES,"While a pokemon uses a move against the user of the move {0}, it loose health points in function by the types of the used move (damage is added if there is some types for the move):");
        e_.add(M_P_44_SUFFERING_TYPES_HP,"Rate of lost life");
        e_.add(M_P_44_SUFFERING_TYPES_T,"Type of the move");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_44_COUNTER_PROTECT,"Lorsqu''un pokemon adverse attaque le lanceur de l''attaque {0}, la contre-attaque de l''effet échoue si et seulement si une des conditions est vérifiée:");
        f_.add(M_P_44_DROPPED_STAT,"Lorsqu''un pokemon adverse attaque le lanceur de l''attaque {0} avec une attaque directe, ses boosts de statistiques varient de la façon suivante:");
        f_.add(M_P_44_DROPPED_STAT_S,"Statistique");
        f_.add(M_P_44_DROPPED_STAT_V,"Variation de la statistique");
        f_.add(M_P_44_EFFECT,"L''effet protège son lanceur contre certains attaques et peut permettre de contre attaquer (retourner certains des effets de ces attaques).");
        f_.add(M_P_44_FAIL_PROTECT,"Lorsqu''un pokemon attaque le lanceur de l''attaque {0}, la protection de l''effet échoue si et seulement si une des conditions est vérifiée:");
        f_.add(M_P_44_FORMULA,"{0}");
        f_.add(M_P_44_SUFFERING_DIRECT,"Lorsqu''un pokemon adverse attaque le lanceur de l''attaque {0} avec une attaque directe, le taux de vie perdue est de {1}.");
        f_.add(M_P_44_SUFFERING_TYPES,"Lorsqu''un pokemon adverse attaque le lanceur de l''attaque {0}, il perd des dégâts en fonction des types de l''attaque utilisée (les degats sont cumules s''il y a plusieurs types pour l''attaque):");
        f_.add(M_P_44_SUFFERING_TYPES_HP,"Taux de vie perdue");
        f_.add(M_P_44_SUFFERING_TYPES_T,"Type de l''attaque");
        return f_;
    }
}