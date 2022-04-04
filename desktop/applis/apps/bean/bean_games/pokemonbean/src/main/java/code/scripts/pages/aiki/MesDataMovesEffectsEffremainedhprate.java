package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffremainedhprate{
private static final String C_P_56_0="This effect makes vary the hp in function of a rate of remaining hp of the fighter.\n";
private static final String C_P_56_1="The target wins {0} of its remaining hp.\n";
private static final String C_P_56_2="The target looses {0} of its remaining hp.\n";
private static final String C_P_56_3="Cet effet fait varier les pv en fonction d''un quota de pv restants du combattant.\n";
private static final String C_P_56_4="La cible gagne {0} de ses pv restants.\n";
private static final String C_P_56_5="La cible perd {0} de ses pv restants.\n";
private static final String M_P_56_EFFECT="effect";
private static final String M_P_56_RATE_LOOSE="rate_loose";
private static final String M_P_56_RATE_WIN="rate_win";
private static final char SEP='=';
private MesDataMovesEffectsEffremainedhprate(){}
static String en(){
String f=M_P_56_EFFECT+SEP+C_P_56_0;
f+=M_P_56_RATE_WIN+SEP+C_P_56_1;
f+=M_P_56_RATE_LOOSE+SEP+C_P_56_2;
return f;
}
static String fr(){
String f=M_P_56_EFFECT+SEP+C_P_56_3;
f+=M_P_56_RATE_WIN+SEP+C_P_56_4;
f+=M_P_56_RATE_LOOSE+SEP+C_P_56_5;
return f;
}
}
