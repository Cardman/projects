package code.scripts.pages.aiki;
final class MesDataEndroundPositionrelation{
private static final String C_P_8_0="This effect regards the position of the user and the possible substitute.\n";
private static final String C_P_8_1="The effect of healing happens one round after at the place of the user at the moment of using.The pokemon that is at the place of the effect wins {0} of its full life.\n";
private static final String C_P_8_2="Cet effet tient compte de la position du lanceur et le remplacant éventuel.\n";
private static final String C_P_8_3="L''effet de soin a lieu un tour après à l''emplacement du lanceur au moment du lancer.Le pokemon qui est placé à l''emplacement de l''effet gagne {0} de sa vie totale.\n";
private static final String M_P_8_EFFECT="effect";
private static final String M_P_8_HEAL_HP="heal_hp";
private static final char SEP='=';
private MesDataEndroundPositionrelation(){}
static String en(){
String f=M_P_8_EFFECT+SEP+C_P_8_0;
f+=M_P_8_HEAL_HP+SEP+C_P_8_1;
return f;
}
static String fr(){
String f=M_P_8_EFFECT+SEP+C_P_8_2;
f+=M_P_8_HEAL_HP+SEP+C_P_8_3;
return f;
}
}
