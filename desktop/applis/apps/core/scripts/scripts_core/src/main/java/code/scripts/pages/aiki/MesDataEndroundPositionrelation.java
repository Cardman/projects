package code.scripts.pages.aiki;
final class MesDataEndroundPositionrelation{
private static final String C_P_8_0="This effect regards the position of the user and the possible substitute.<br/>\n";
private static final String C_P_8_1="The effect of healing happens one round after at the place of the user at the moment of using.<br/>The pokemon that is at the place of the effect wins {0} of its full life.<br/>\n";
private static final String C_P_8_2="Cet effet tient compte de la position du lanceur et le remplacant &eacute;ventuel.<br/>\n";
private static final String C_P_8_3="L''effet de soin a lieu un tour apr&egrave;s &agrave; l''emplacement du lanceur au moment du lancer.<br/>Le pokemon qui est plac&eacute; &agrave; l''emplacement de l''effet gagne {0} de sa vie totale.<br/>\n";
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
