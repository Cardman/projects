package code.scripts.pages.aiki;
final class MesDataEndroundFoe{
private static final String C_P_5_0="This effect regards the foe team of the user.\n";
private static final String C_P_5_1="The target looses {0} of its full hp.\n";
private static final String C_P_5_2="Cet effet concerne l''équipe adverse du lanceur.\n";
private static final String C_P_5_3="La cible perd {0} de ses pv max.\n";
private static final String M_P_5_EFFECT="effect";
private static final String M_P_5_FOE="foe";
private static final char SEP='=';
private MesDataEndroundFoe(){}
static String en(){
String f=M_P_5_EFFECT+SEP+C_P_5_0;
f+=M_P_5_FOE+SEP+C_P_5_1;
return f;
}
static String fr(){
String f=M_P_5_EFFECT+SEP+C_P_5_2;
f+=M_P_5_FOE+SEP+C_P_5_3;
return f;
}
}
