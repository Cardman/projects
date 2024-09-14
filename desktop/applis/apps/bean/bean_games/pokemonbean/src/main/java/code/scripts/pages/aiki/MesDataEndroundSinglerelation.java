package code.scripts.pages.aiki;
final class MesDataEndroundSinglerelation{
private static final String C_P_10_0="This effect regards the user and the target. If the user or the target switch then the effect is stopped.\n";
private static final String C_P_10_10="Taux de vie perdue par la cible\n";
private static final String C_P_10_11="Tableau des probabilités en fonction du nombre de tours:\n";
private static final String C_P_10_12="Nombre de tours depuis le début de l''effet\n";
private static final String C_P_10_13="Probabilité\n";
private static final String C_P_10_1="Table of the rate of lost hp of the target in function by the number of rounds:\n";
private static final String C_P_10_2="Number of rounds since the beginning of the effect\n";
private static final String C_P_10_3="Rate of lost life by the target\n";
private static final String C_P_10_4="Table of law of number of rounds:\n";
private static final String C_P_10_5="Number of rounds since the beginning of the effect\n";
private static final String C_P_10_6="Rate\n";
private static final String C_P_10_7="Cet effet concerne le lanceur et la cible. Si le lanceur ou la cible switchent alors l''effet est arrêté.\n";
private static final String C_P_10_8="Tableau du taux de pv perdus de la cible en fonction du nombre de tours:\n";
private static final String C_P_10_9="Nombre de tours depuis le début de l''effet\n";
private static final String M_P_10_EFFECT="effect";
private static final String M_P_10_LAW="law";
private static final String M_P_10_LAW_RD="law_rd";
private static final String M_P_10_LAW_VALUE="law_value";
private static final String M_P_10_SUFFERED="suffered";
private static final String M_P_10_SUFFERED_RATE="suffered_rate";
private static final String M_P_10_SUFFERED_RD="suffered_rd";
private static final char SEP='=';
private MesDataEndroundSinglerelation(){}
static String en(){
String f=M_P_10_EFFECT+SEP+C_P_10_0;
f+=M_P_10_SUFFERED+SEP+C_P_10_1;
f+=M_P_10_SUFFERED_RD+SEP+C_P_10_2;
f+=M_P_10_SUFFERED_RATE+SEP+C_P_10_3;
f+=M_P_10_LAW+SEP+C_P_10_4;
f+=M_P_10_LAW_RD+SEP+C_P_10_5;
f+=M_P_10_LAW_VALUE+SEP+C_P_10_6;
return f;
}
static String fr(){
String f=M_P_10_EFFECT+SEP+C_P_10_7;
f+=M_P_10_SUFFERED+SEP+C_P_10_8;
f+=M_P_10_SUFFERED_RD+SEP+C_P_10_9;
f+=M_P_10_SUFFERED_RATE+SEP+C_P_10_10;
f+=M_P_10_LAW+SEP+C_P_10_11;
f+=M_P_10_LAW_RD+SEP+C_P_10_12;
f+=M_P_10_LAW_VALUE+SEP+C_P_10_13;
return f;
}
}
