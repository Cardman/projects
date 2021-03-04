package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffcopymove{
private MesDataMovesEffectsEffcopymove(){}
static String en(){
String f="effect=The effect happens lors d''une copie d''attque de combattant\n";
f+="no_effect=The effect does not happen if the target has not used any move; or if the last used move of the target is <a c:command=\"$clickDefaultMove\">{0}</a>.<br/>\n";
f+="no_effect_2=The effect does not happen if the firstly chosen move of the user is <a c:command=\"$clickDefaultMove\">{0}</a>.<br/>\n";
f+="moves_not_copied=The following moves cannot be copied:<br/>\n";
f+="copy_tmp_move=The copied move of the target substitutes the move {0} of the user and has {1} pp at the beginning. If all pp of the copied move are used then the user can again use the move {0} with one pp less.<br/>\n";
f+="copy_def_move=The copied move of the target definitively substitutes the move {0} of the user except if the user is under the effect of one of the moves like:<br/>\n";
f+="copy_def_move_without_trans=The copied move of the target definitively substitutes the move {0} of the user.<br/>\n";
return f;
}
static String fr(){
String f="effect=L''effet a lieu lors d''une copie d''attque de combattant\n";
f+="no_effect=L''effet n''a pas lieu si la cible n''a pas attaqu&eacute; ou si la derni&egrave;re attaque lanc&eacute;e de la cible est <a c:command=\"$clickDefaultMove\">{0}</a>.<br/>\n";
f+="no_effect_2=L''effet n''a pas lieu si l''attaque initialement choisie du lanceur est <a c:command=\"$clickDefaultMove\">{0}</a>.<br/>\n";
f+="moves_not_copied=Les attaques suivantes ne peuvent pas &ecirc;tre copi&eacute;es:<br/>\n";
f+="copy_tmp_move=L''attaque copi&eacute;e de la cible vient substituer l''attaque {0} du lanceur et prend {1} pp au d&eacute;part. Si tous les pp de l''attaque copi&eacute;e sont consomm&eacute;s alors le lanceur peut de nouveau utiliser l''attaque {0} avec un pp de moins.<br/>\n";
f+="copy_def_move=L''attaque copi&eacute;e de la cible vient substituer l''attaque {0} du lanceur d&eacute;finitivement sauf si le lanceur est sous l''effet d''une des attaques comme:<br/>\n";
f+="copy_def_move_without_trans=L''attaque copi&eacute;e de la cible vient substituer l''attaque {0} du lanceur d&eacute;finitivement.<br/>\n";
return f;
}
}
