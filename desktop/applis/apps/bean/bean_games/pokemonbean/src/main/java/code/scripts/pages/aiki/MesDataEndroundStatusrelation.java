package code.scripts.pages.aiki;
final class MesDataEndroundStatusrelation{
private static final String C_P_12_0="This effect regards the user and the target. If the user or the target switch then the effect is stopped.\n";
private static final String C_P_12_1="The user absorbs {0} of full hp of the target.\n";
private static final String C_P_12_2="Cet effet concerne le lanceur et la cible. Si le lanceur ou la cible switchent alors l''effet est arrêté.\n";
private static final String C_P_12_3="Le lanceur absorbe {0} des pv max de la cible.\n";
private static final String M_P_12_EFFECT="effect";
private static final String M_P_12_THIEVED_HP_RATE="thieved_hp_rate";
private static final char SEP='=';
private MesDataEndroundStatusrelation(){}
static String en(){
String f=M_P_12_EFFECT+SEP+C_P_12_0;
f+=M_P_12_THIEVED_HP_RATE+SEP+C_P_12_1;
return f;
}
static String fr(){
String f=M_P_12_EFFECT+SEP+C_P_12_2;
f+=M_P_12_THIEVED_HP_RATE+SEP+C_P_12_3;
return f;
}
}
