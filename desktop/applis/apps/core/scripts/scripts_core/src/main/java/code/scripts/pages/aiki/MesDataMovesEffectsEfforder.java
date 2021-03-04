package code.scripts.pages.aiki;
final class MesDataMovesEffectsEfforder{
private MesDataMovesEffectsEfforder(){}
static String en(){
String f="effect=This effect changes the order of the fighters.<br/>\n";
f+="after_user=If the target is not KO, the target uses a move after the user.\n";
f+="last=If the user is not knocked out after the use of its move, the target lastly uses a move.<br/>If it exists some pokemon targetted by this move, they will use a move in the same order than the other one.<br/>If the user of this move is knocked out before the target using a move and if the target is not KO, then the effect is stopped.<br/>\n";
return f;
}
static String fr(){
String f="effect=Cet effet change l''ordre d''attaque des combattants.<br/>\n";
f+="after_user=Si la cible n''est pas KO, la cible attaque apr&egrave;s le lanceur.\n";
f+="last=Si le lanceur ne tombe pas KO apr&egrave;s son attaque, la cible attaque en dernier.<br/>S''il existe plusieurs pok&eacute;mons cibles de cette attaque, il attaqueront dans le m&ecirc;me ordre que les autres.<br/>Si le lanceur de cette attaque tombe KO avant l''attaque de la cible et si la cible n''est pas KO, alors l''effet est arr&ecirc;t&eacute;.<br/>\n";
return f;
}
}
