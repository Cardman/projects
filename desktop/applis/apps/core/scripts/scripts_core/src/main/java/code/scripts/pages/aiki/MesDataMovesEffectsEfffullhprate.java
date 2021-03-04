package code.scripts.pages.aiki;
final class MesDataMovesEffectsEfffullhprate{
private MesDataMovesEffectsEfffullhprate(){}
static String en(){
String f="effect=This effect makes vary the hp in function by a rate of full hp of the fighter.<br/>\n";
f+="restored=Here is the rate of full restored hp of the user:<br/>\n";
f+="formula={0}\n";
f+="left_user_hp=The user decreases {0} of its full hp.<br/>\n";
f+="closest_foe_damage_rate_hp=Each foe fighter close to the target looses {0} of its full hp.<br/>\n";
return f;
}
static String fr(){
String f="effect=Cet effet fait varier les pv en fonction d''un quota de pv totaux du combattant.<br/>\n";
f+="restored=Voici le taux des pv max restaur&eacute;s du lanceur:<br/>\n";
f+="formula={0}\n";
f+="left_user_hp=Le lanceur sacrifie {0} de ses pv max.<br/>\n";
f+="closest_foe_damage_rate_hp=Chaque combattant adverse adjacent &agrave; la cible perd {0} de ses pv max.<br/>\n";
return f;
}
}
