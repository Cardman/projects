package code.scripts.pages.aiki;
final class MesDataItemsBoost{
private MesDataItemsBoost(){}
static String en(){
String f="win_pp=Used on a move, this move wins {0} pp.<br/>\n";
f+="happiness=Here is the table of happiness points depending on the BALL having caught the pokemon to be boosted:<br/>\n";
f+="happiness_ball=BALL having caught\n";
f+="happiness_other_ball=Other used BALL or none\n";
f+="happiness_boost=Won happiness points\n";
f+="evs=Here is the table of won evs by statistic if evs of the statistic to be increased are lower or equal to {0}:<br/>\n";
f+="evs_stat=Statistic\n";
f+="evs_boost=Won evs\n";
return f;
}
static String fr(){
String f="win_pp=Utilis&eacute; sur une attaque, celle-ci gagne {0} pp.<br/>\n";
f+="happiness=Voici le tableau des points de bonheur gagn&eacute;s en fonction de la BALL de capture du pok&eacute;mon &agrave; booster:<br/>\n";
f+="happiness_ball=BALL de capture\n";
f+="happiness_other_ball=Autre BALL de capture ou aucune\n";
f+="happiness_boost=Points de bonheur gagn&eacute;s\n";
f+="evs=Voici le tableau des evs gagn&eacute;s par statistique si les evs de la statistique &agrave; augmenter sont inf&eacute;rieurs ou &eacute;gaux &agrave; {0}:<br/>\n";
f+="evs_stat=Statistique\n";
f+="evs_boost=Evs gagn&eacute;s\n";
return f;
}
}
