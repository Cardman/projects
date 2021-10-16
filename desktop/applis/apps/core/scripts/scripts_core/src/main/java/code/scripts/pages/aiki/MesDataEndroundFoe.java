package code.scripts.pages.aiki;
final class MesDataEndroundFoe{
private static final String C_P_5_0="This effect regards the foe team of the user.<br/>\n";
private static final String C_P_5_1="The target looses {0} of its full hp.<br/>\n";
private static final String C_P_5_2="Cet effet concerne l''&eacute;quipe adverse du lanceur.<br/>\n";
private static final String C_P_5_3="La cible perd {0} de ses pv max.<br/>\n";
private static final String M_P_EFFECT="effect";
private static final String M_P_FOE="foe";
private static final char SEP='=';
private MesDataEndroundFoe(){}
static String en(){
String f=M_P_EFFECT+SEP+C_P_5_0;
f+=M_P_FOE+SEP+C_P_5_1;
return f;
}
static String fr(){
String f=M_P_EFFECT+SEP+C_P_5_2;
f+=M_P_FOE+SEP+C_P_5_3;
return f;
}
}
