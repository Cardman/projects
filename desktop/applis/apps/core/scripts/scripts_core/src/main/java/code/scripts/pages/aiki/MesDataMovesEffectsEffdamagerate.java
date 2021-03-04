package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffdamagerate{
private MesDataMovesEffectsEffdamagerate(){}
static String en(){
String f="effect=This effect makes vary the hp of the user in function by inflicted damage against the target.<br/>\n";
f+="pos_rate=The rate of damage points inflicted against the target won by the user is {0}.<br/>\n";
f+="neg_rate=The rate of damage points inflicted against the target lost by the user is {0}.<br/>\n";
return f;
}
static String fr(){
String f="effect=Cet effet fait varier les pv du lanceur en fonction des d&eacute;g&acirc;ts inflig&eacute;s &agrave; la cible.<br/>\n";
f+="pos_rate=Le taux de points de d&eacute;g&acirc;ts inflig&eacute;s &agrave; la cible gagn&eacute;s par le lanceur est de {0}.<br/>\n";
f+="neg_rate=Le taux de points de d&eacute;g&acirc;ts inflig&eacute;s &agrave; la cible perdus par le lanceur est de {0}.<br/>\n";
return f;
}
}
