package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffmultusedmovepower{
private static final String C_P_52_0="This effect multiplies the power of the used moves of the user in fonction by the type.\n";
private static final String C_P_52_1="Here is the table of the rates of the power of the moves of the user in function by the type:\n";
private static final String C_P_52_2="Type of move\n";
private static final String C_P_52_3="Rate\n";
private static final String C_P_52_4="Cet effet multiplie la puissance des attaques du lanceur en fonction du type.\n";
private static final String C_P_52_5="Voici le tableau des coefficients des puissances des attaques du lanceur en fonction du type:\n";
private static final String C_P_52_6="Type d''attaque\n";
private static final String C_P_52_7="Coefficient\n";
private static final String M_P_52_EFFECT="effect";
private static final String M_P_52_MULT_POWER="mult_power";
private static final String M_P_52_MULT_POWER_RATE="mult_power_rate";
private static final String M_P_52_MULT_POWER_TYPE="mult_power_type";
private static final char SEP='=';
private MesDataMovesEffectsEffmultusedmovepower(){}
static String en(){
String f=M_P_52_EFFECT+SEP+C_P_52_0;
f+=M_P_52_MULT_POWER+SEP+C_P_52_1;
f+=M_P_52_MULT_POWER_TYPE+SEP+C_P_52_2;
f+=M_P_52_MULT_POWER_RATE+SEP+C_P_52_3;
return f;
}
static String fr(){
String f=M_P_52_EFFECT+SEP+C_P_52_4;
f+=M_P_52_MULT_POWER+SEP+C_P_52_5;
f+=M_P_52_MULT_POWER_TYPE+SEP+C_P_52_6;
f+=M_P_52_MULT_POWER_RATE+SEP+C_P_52_7;
return f;
}
}
