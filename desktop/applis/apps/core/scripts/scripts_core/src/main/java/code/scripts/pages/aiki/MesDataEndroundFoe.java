package code.scripts.pages.aiki;
final class MesDataEndroundFoe{
private MesDataEndroundFoe(){}
static String en(){
String f="effect=This effect regards the foe team of the user.<br/>\n";
f+="foe=The target looses {0} of its full hp.<br/>\n";
return f;
}
static String fr(){
String f="effect=Cet effet concerne l''&eacute;quipe adverse du lanceur.<br/>\n";
f+="foe=La cible perd {0} de ses pv max.<br/>\n";
return f;
}
}
