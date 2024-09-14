package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffdamagerate{
private static final String C_P_46_0="This effect makes vary the hp of the user in function by inflicted damage against the target.\n";
private static final String C_P_46_1="The rate of damage points inflicted against the target won by the user is {0}.\n";
private static final String C_P_46_2="The rate of damage points inflicted against the target lost by the user is {0}.\n";
private static final String C_P_46_3="Cet effet fait varier les pv du lanceur en fonction des dégâts infligés à la cible.\n";
private static final String C_P_46_4="Le taux de points de dégâts infligés à la cible gagnés par le lanceur est de {0}.\n";
private static final String C_P_46_5="Le taux de points de dégâts infligés à la cible perdus par le lanceur est de {0}.\n";
private static final String M_P_46_EFFECT="effect";
private static final String M_P_46_NEG_RATE="neg_rate";
private static final String M_P_46_POS_RATE="pos_rate";
private static final char SEP='=';
private MesDataMovesEffectsEffdamagerate(){}
static String en(){
String f=M_P_46_EFFECT+SEP+C_P_46_0;
f+=M_P_46_POS_RATE+SEP+C_P_46_1;
f+=M_P_46_NEG_RATE+SEP+C_P_46_2;
return f;
}
static String fr(){
String f=M_P_46_EFFECT+SEP+C_P_46_3;
f+=M_P_46_POS_RATE+SEP+C_P_46_4;
f+=M_P_46_NEG_RATE+SEP+C_P_46_5;
return f;
}
}
