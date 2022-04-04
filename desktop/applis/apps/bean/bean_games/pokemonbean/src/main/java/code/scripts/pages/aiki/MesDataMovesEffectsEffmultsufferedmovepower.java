package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffmultsufferedmovepower{
private static final String C_P_51_0="This effect multiplies the power of the suffered moves of the target in function by the type.\n";
private static final String C_P_51_1="Here is the table of the rates of the power of the moves against the target in function by the type:\n";
private static final String C_P_51_2="Type of move\n";
private static final String C_P_51_3="Rate\n";
private static final String C_P_51_4="Cet effet multiplie la puissance des attaques subies de la cible en fonction du type.\n";
private static final String C_P_51_5="Voici le tableau des coefficients des puissances des attaques contre la cible en fonction du type:\n";
private static final String C_P_51_6="Type d''attaque\n";
private static final String C_P_51_7="Coefficient\n";
private static final String M_P_51_EFFECT="effect";
private static final String M_P_51_MULT_POWER="mult_power";
private static final String M_P_51_MULT_POWER_RATE="mult_power_rate";
private static final String M_P_51_MULT_POWER_TYPE="mult_power_type";
private static final char SEP='=';
private MesDataMovesEffectsEffmultsufferedmovepower(){}
static String en(){
String f=M_P_51_EFFECT+SEP+C_P_51_0;
f+=M_P_51_MULT_POWER+SEP+C_P_51_1;
f+=M_P_51_MULT_POWER_TYPE+SEP+C_P_51_2;
f+=M_P_51_MULT_POWER_RATE+SEP+C_P_51_3;
return f;
}
static String fr(){
String f=M_P_51_EFFECT+SEP+C_P_51_4;
f+=M_P_51_MULT_POWER+SEP+C_P_51_5;
f+=M_P_51_MULT_POWER_TYPE+SEP+C_P_51_6;
f+=M_P_51_MULT_POWER_RATE+SEP+C_P_51_7;
return f;
}
}
