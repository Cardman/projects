package code.scripts.pages.aiki;
final class MesDataMovesEffectsEfffullhprate{
private static final String C_P_48_0="This effect makes vary the hp in function by a rate of full hp of the fighter.<br/>\n";
private static final String C_P_48_1="Here is the rate of full restored hp of the user:<br/>\n";
private static final String C_P_48_2="{0}\n";
private static final String C_P_48_3="The user decreases {0} of its full hp.<br/>\n";
private static final String C_P_48_4="Each foe fighter close to the target looses {0} of its full hp.<br/>\n";
private static final String C_P_48_5="Cet effet fait varier les pv en fonction d''un quota de pv totaux du combattant.<br/>\n";
private static final String C_P_48_6="Voici le taux des pv max restaur&eacute;s du lanceur:<br/>\n";
private static final String C_P_48_7="{0}\n";
private static final String C_P_48_8="Le lanceur sacrifie {0} de ses pv max.<br/>\n";
private static final String C_P_48_9="Chaque combattant adverse adjacent &agrave; la cible perd {0} de ses pv max.<br/>\n";
private static final String M_P_48_CLOSEST_FOE_DAMAGE_RATE_HP="closest_foe_damage_rate_hp";
private static final String M_P_48_EFFECT="effect";
private static final String M_P_48_FORMULA="formula";
private static final String M_P_48_LEFT_USER_HP="left_user_hp";
private static final String M_P_48_RESTORED="restored";
private static final char SEP='=';
private MesDataMovesEffectsEfffullhprate(){}
static String en(){
String f=M_P_48_EFFECT+SEP+C_P_48_0;
f+=M_P_48_RESTORED+SEP+C_P_48_1;
f+=M_P_48_FORMULA+SEP+C_P_48_2;
f+=M_P_48_LEFT_USER_HP+SEP+C_P_48_3;
f+=M_P_48_CLOSEST_FOE_DAMAGE_RATE_HP+SEP+C_P_48_4;
return f;
}
static String fr(){
String f=M_P_48_EFFECT+SEP+C_P_48_5;
f+=M_P_48_RESTORED+SEP+C_P_48_6;
f+=M_P_48_FORMULA+SEP+C_P_48_7;
f+=M_P_48_LEFT_USER_HP+SEP+C_P_48_8;
f+=M_P_48_CLOSEST_FOE_DAMAGE_RATE_HP+SEP+C_P_48_9;
return f;
}
}
