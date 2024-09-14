package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffcounterattack{
private static final String C_P_44_0="The effect protects its user against some moves and can let its user counter attack (return some effects of these moves).\n";
private static final String C_P_44_10="{0}\n";
private static final String C_P_44_11="L''effet protège son lanceur contre certains attaques et peut permettre de contre attaquer (retourner certains des effets de ces attaques).\n";
private static final String C_P_44_12="Lorsqu''un pokemon attaque le lanceur de l''attaque {0}, la protection de l''effet échoue si et seulement si une des conditions est vérifiée:\n";
private static final String C_P_44_13="Lorsqu''un pokemon adverse attaque le lanceur de l''attaque {0}, la contre-attaque de l''effet échoue si et seulement si une des conditions est vérifiée:\n";
private static final String C_P_44_14="Lorsqu''un pokemon adverse attaque le lanceur de l''attaque {0}, il perd des dégâts en fonction des types de l''attaque utilisée (les degats sont cumules s''il y a plusieurs types pour l''attaque):\n";
private static final String C_P_44_15="Type de l''attaque\n";
private static final String C_P_44_16="Taux de vie perdue\n";
private static final String C_P_44_17="Lorsqu''un pokemon adverse attaque le lanceur de l''attaque {0} avec une attaque directe, ses boosts de statistiques varient de la façon suivante:\n";
private static final String C_P_44_18="Statistique\n";
private static final String C_P_44_19="Variation de la statistique\n";
private static final String C_P_44_1="While a pokemon uses a move against the user of the move {0}, the protection of the effect fails if and only if one of the conditions is checked:\n";
private static final String C_P_44_20="Lorsqu''un pokemon adverse attaque le lanceur de l''attaque {0} avec une attaque directe, le taux de vie perdue est de {1}.\n";
private static final String C_P_44_21="{0}\n";
private static final String C_P_44_2="While a pokemon uses a move against the user of the move {0}, the counter attack of the effect fails if and only if one of the conditions is checked:\n";
private static final String C_P_44_3="While a pokemon uses a move against the user of the move {0}, it loose health points in function by the types of the used move (damage is added if there is some types for the move):\n";
private static final String C_P_44_4="Type of the move\n";
private static final String C_P_44_5="Rate of lost life\n";
private static final String C_P_44_6="While a pokemon uses a move against the user of the move {0} with a direct move, the levels of its statistics vary by the following way:\n";
private static final String C_P_44_7="Statistic\n";
private static final String C_P_44_8="Variation of the statistic\n";
private static final String C_P_44_9="While a pokemon uses a move against the user of the move {0} with a direct move, the rate of lost life is {1}.\n";
private static final String M_P_44_COUNTER_PROTECT="counter_protect";
private static final String M_P_44_DROPPED_STAT="dropped_stat";
private static final String M_P_44_DROPPED_STAT_S="dropped_stat_s";
private static final String M_P_44_DROPPED_STAT_V="dropped_stat_v";
private static final String M_P_44_EFFECT="effect";
private static final String M_P_44_FAIL_PROTECT="fail_protect";
private static final String M_P_44_FORMULA="formula";
private static final String M_P_44_SUFFERING_DIRECT="suffering_direct";
private static final String M_P_44_SUFFERING_TYPES="suffering_types";
private static final String M_P_44_SUFFERING_TYPES_HP="suffering_types_hp";
private static final String M_P_44_SUFFERING_TYPES_T="suffering_types_t";
private static final char SEP='=';
private MesDataMovesEffectsEffcounterattack(){}
static String en(){
String f=M_P_44_EFFECT+SEP+C_P_44_0;
f+=M_P_44_FAIL_PROTECT+SEP+C_P_44_1;
f+=M_P_44_COUNTER_PROTECT+SEP+C_P_44_2;
f+=M_P_44_SUFFERING_TYPES+SEP+C_P_44_3;
f+=M_P_44_SUFFERING_TYPES_T+SEP+C_P_44_4;
f+=M_P_44_SUFFERING_TYPES_HP+SEP+C_P_44_5;
f+=M_P_44_DROPPED_STAT+SEP+C_P_44_6;
f+=M_P_44_DROPPED_STAT_S+SEP+C_P_44_7;
f+=M_P_44_DROPPED_STAT_V+SEP+C_P_44_8;
f+=M_P_44_SUFFERING_DIRECT+SEP+C_P_44_9;
f+=M_P_44_FORMULA+SEP+C_P_44_10;
return f;
}
static String fr(){
String f=M_P_44_EFFECT+SEP+C_P_44_11;
f+=M_P_44_FAIL_PROTECT+SEP+C_P_44_12;
f+=M_P_44_COUNTER_PROTECT+SEP+C_P_44_13;
f+=M_P_44_SUFFERING_TYPES+SEP+C_P_44_14;
f+=M_P_44_SUFFERING_TYPES_T+SEP+C_P_44_15;
f+=M_P_44_SUFFERING_TYPES_HP+SEP+C_P_44_16;
f+=M_P_44_DROPPED_STAT+SEP+C_P_44_17;
f+=M_P_44_DROPPED_STAT_S+SEP+C_P_44_18;
f+=M_P_44_DROPPED_STAT_V+SEP+C_P_44_19;
f+=M_P_44_SUFFERING_DIRECT+SEP+C_P_44_20;
f+=M_P_44_FORMULA+SEP+C_P_44_21;
return f;
}
}
