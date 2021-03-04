package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffvarpp{
private MesDataMovesEffectsEffvarpp(){}
static String en(){
String f="effect=This effect affects the pp of moves of a fighter.<br/>\n";
f+="delete_pp=The target looses {0} pp of full pp of its last successful move.<br/>If the last successful move by the target owns less than {0} pp of the full pp, then the number of pp becomes zero.<br/>\n";
return f;
}
static String fr(){
String f="effect=Cet effet touche aux pp d''un combattant.<br/>\n";
f+="delete_pp=La cible perd {0} pp des pp max de sa derni&egrave;re attaque r&eacute;ussie.<br/>Si la derni&egrave;re attaque r&eacute;ussie de la cible poss&egrave;de moins de {0} pp des pp max, alors le nombre de pp devient nul.<br/>\n";
return f;
}
}
