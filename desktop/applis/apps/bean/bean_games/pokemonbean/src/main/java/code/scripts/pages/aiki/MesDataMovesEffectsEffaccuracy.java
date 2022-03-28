package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffaccuracy{
private static final String C_P_37_0="This move lets to achieve targets disappearing during the next round this effect.<br/>\n";
private static final String C_P_37_1="The accuracy of the next used move against the target is 100%.<br/>\n";
private static final String C_P_37_2="Cette attaque permet de toucher des cibles disparaissant pendant le tour suivant cet effet.<br/>\n";
private static final String C_P_37_3="La pr&eacute;cision de la prochaine attaque utilis&eacute;e sur la cible vaut 100%.<br/>\n";
private static final String M_P_37_ACCURACY_MAX="accuracy_max";
private static final String M_P_37_EFFECT="effect";
private static final char SEP='=';
private MesDataMovesEffectsEffaccuracy(){}
static String en(){
String f=M_P_37_EFFECT+SEP+C_P_37_0;
f+=M_P_37_ACCURACY_MAX+SEP+C_P_37_1;
return f;
}
static String fr(){
String f=M_P_37_EFFECT+SEP+C_P_37_2;
f+=M_P_37_ACCURACY_MAX+SEP+C_P_37_3;
return f;
}
}
