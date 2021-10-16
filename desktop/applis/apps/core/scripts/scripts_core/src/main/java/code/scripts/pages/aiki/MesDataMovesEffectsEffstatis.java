package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffstatis{
private static final String C_P_58_0="This effect changes levels or values of statistics for a fighter at least.<br/>\n";
private static final String C_P_58_10="The following lowered statistics of the fighter are now {0}:<br/>\n";
private static final String C_P_58_11="The user copies the levels of the following statistics of the target:<br/>\n";
private static final String C_P_58_12="{0}\n";
private static final String C_P_58_13="Cet effet change des boosts ou des valeurs de statistiques pour au moins un combattant.<br/>\n";
private static final String C_P_58_14="Voici le tableau de variations des statistiques de la cible:<br/>\n";
private static final String C_P_58_15="Statistique\n";
private static final String C_P_58_16="Variation du boost de la statistique\n";
private static final String C_P_58_17="Probabilit&eacute;\n";
private static final String C_P_58_18="L''effet a toujours lieu.<br/>\n";
private static final String C_P_58_19="L''effet a lieu avec une probabilit&eacute; de {0} soit environ de {1} %.<br/>\n";
private static final String C_P_58_1="Here is the table of variations of statistics of the target:<br/>\n";
private static final String C_P_58_20="Conditions suffisantes d''&eacute;chec\n";
private static final String C_P_58_21="Le lanceur et la cible &eacute;changent les boosts des statistiques suivantes:<br/>\n";
private static final String C_P_58_22="Les statistiques du combattant suivantes sont remises &agrave; {0}:<br/>\n";
private static final String C_P_58_23="Les statistiques baiss&eacute;es du combattant suivantes sont remises &agrave; {0}:<br/>\n";
private static final String C_P_58_24="Le lanceur copie les boosts des statistiques de la cible suivantes:<br/>\n";
private static final String C_P_58_25="{0}\n";
private static final String C_P_58_2="Statistic\n";
private static final String C_P_58_3="Variation of the level of the statistic\n";
private static final String C_P_58_4="Rate\n";
private static final String C_P_58_5="The effect always happens.<br/>\n";
private static final String C_P_58_6="The effect happens with a rate of {0} being about {1} %.<br/>\n";
private static final String C_P_58_7="Sufficient conditions of fail\n";
private static final String C_P_58_8="The user and the target switch the levels of the following statistics:<br/>\n";
private static final String C_P_58_9="The following statistics of the fighter are now {0}:<br/>\n";
private static final String M_P_ALWAYS_ENABLED="always_enabled";
private static final String M_P_BOOST="boost";
private static final String M_P_CANCEL_CHGT_STAT="cancel_chgt_stat";
private static final String M_P_CANCEL_LOW_STAT="cancel_low_stat";
private static final String M_P_COPY_BOOST="copy_boost";
private static final String M_P_EFFECT="effect";
private static final String M_P_FAIL="fail";
private static final String M_P_FORMULA="formula";
private static final String M_P_RATE_ENABLED="rate_enabled";
private static final String M_P_RATE_EVENT="rate_event";
private static final String M_P_STATISTIC="statistic";
private static final String M_P_SWAP_BOOST="swap_boost";
private static final String M_P_VAR_STATIS_RANK="var_statis_rank";
private static final char SEP='=';
private MesDataMovesEffectsEffstatis(){}
static String en(){
String f=M_P_EFFECT+SEP+C_P_58_0;
f+=M_P_VAR_STATIS_RANK+SEP+C_P_58_1;
f+=M_P_STATISTIC+SEP+C_P_58_2;
f+=M_P_BOOST+SEP+C_P_58_3;
f+=M_P_RATE_EVENT+SEP+C_P_58_4;
f+=M_P_ALWAYS_ENABLED+SEP+C_P_58_5;
f+=M_P_RATE_ENABLED+SEP+C_P_58_6;
f+=M_P_FAIL+SEP+C_P_58_7;
f+=M_P_SWAP_BOOST+SEP+C_P_58_8;
f+=M_P_CANCEL_CHGT_STAT+SEP+C_P_58_9;
f+=M_P_CANCEL_LOW_STAT+SEP+C_P_58_10;
f+=M_P_COPY_BOOST+SEP+C_P_58_11;
f+=M_P_FORMULA+SEP+C_P_58_12;
return f;
}
static String fr(){
String f=M_P_EFFECT+SEP+C_P_58_13;
f+=M_P_VAR_STATIS_RANK+SEP+C_P_58_14;
f+=M_P_STATISTIC+SEP+C_P_58_15;
f+=M_P_BOOST+SEP+C_P_58_16;
f+=M_P_RATE_EVENT+SEP+C_P_58_17;
f+=M_P_ALWAYS_ENABLED+SEP+C_P_58_18;
f+=M_P_RATE_ENABLED+SEP+C_P_58_19;
f+=M_P_FAIL+SEP+C_P_58_20;
f+=M_P_SWAP_BOOST+SEP+C_P_58_21;
f+=M_P_CANCEL_CHGT_STAT+SEP+C_P_58_22;
f+=M_P_CANCEL_LOW_STAT+SEP+C_P_58_23;
f+=M_P_COPY_BOOST+SEP+C_P_58_24;
f+=M_P_FORMULA+SEP+C_P_58_25;
return f;
}
}
