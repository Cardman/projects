package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffstatus{
private MesDataMovesEffectsEffstatus(){}
static String en(){
String f="effect=This effect adds/deletes a status at least.<br/>\n";
f+="law_status=Here is the law of editing status to add to the target:<br/>\n";
f+="status=Status\n";
f+="fail=Sufficient conditions of fail\n";
f+="rate_event=Rate\n";
f+="other_status=No added status\n";
f+="deleted_status=The deleted status of the target are the following one:<br/>\n";
f+="ko_user=The user is knocked out. The substitute of the user is full healed (pp, hp, status).<br/>\n";
f+="forward=The status of the user are forwarded to the target that does not have.<br/>\n";
f+="formula={0}\n";
return f;
}
static String fr(){
String f="effect=Cet effet ajoute/supprime au moins un statut.<br/>\n";
f+="law_status=Voici la loi de tirage des statuts &agrave; ajouter &agrave; la cible:<br/>\n";
f+="status=Statut\n";
f+="fail=Conditions suffisantes d''&eacute;chec\n";
f+="rate_event=Probabilit&eacute;\n";
f+="other_status=Aucun statut suppl&eacute;mentaire\n";
f+="deleted_status=Les statuts de la cible supprim&eacute;s sont les suivants:<br/>\n";
f+="ko_user=Le lanceur tombe KO. Le rempla&ccedil;ant du lanceur est totalement soign&eacute; (pp, pv, statuts).<br/>\n";
f+="forward=Les statuts du lanceur sont transf&eacute;r&eacute;s vers la cible qu''elle n''a pas.<br/>\n";
f+="formula={0}\n";
return f;
}
}
