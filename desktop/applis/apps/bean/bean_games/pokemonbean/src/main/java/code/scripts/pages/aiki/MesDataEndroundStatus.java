package code.scripts.pages.aiki;
final class MesDataEndroundStatus{
private static final String C_P_11_0="This effect regards one fighter.\n";
private static final String C_P_11_1="The target looses {0} of its full hp.\n";
private static final String C_P_11_2="Cet effet concerne un seul combattant.\n";
private static final String C_P_11_3="La cible perd {0} de ses pv max.\n";
private static final String M_P_11_EFFECT="effect";
private static final String M_P_11_INFLICTED_RATE_HP_TARGET="inflicted_rate_hp_target";
private static final char SEP='=';
private MesDataEndroundStatus(){}
static String en(){
String f=M_P_11_EFFECT+SEP+C_P_11_0;
f+=M_P_11_INFLICTED_RATE_HP_TARGET+SEP+C_P_11_1;
return f;
}
static String fr(){
String f=M_P_11_EFFECT+SEP+C_P_11_2;
f+=M_P_11_INFLICTED_RATE_HP_TARGET+SEP+C_P_11_3;
return f;
}
}
