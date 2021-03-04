package code.scripts.pages.aiki;
final class MesDataEndroundStatus{
private MesDataEndroundStatus(){}
static String en(){
String f="effect=This effect regards one fighter.<br/>\n";
f+="inflicted_rate_hp_target=The target looses {0} of its full hp.<br/>\n";
return f;
}
static String fr(){
String f="effect=Cet effet concerne un seul combattant.<br/>\n";
f+="inflicted_rate_hp_target=La cible perd {0} de ses pv max.<br/>\n";
return f;
}
}
