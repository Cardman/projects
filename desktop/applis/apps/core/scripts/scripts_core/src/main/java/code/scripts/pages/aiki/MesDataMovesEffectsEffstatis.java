package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffstatis{
private MesDataMovesEffectsEffstatis(){}
static String en(){
String f="effect=This effect changes levels or values of statistics for a fighter at least.<br/>\n";
f+="var_statis_rank=Here is the table of variations of statistics of the target:<br/>\n";
f+="statistic=Statistic\n";
f+="boost=Variation of the level of the statistic\n";
f+="rate_event=Rate\n";
f+="always_enabled=The effect always happens.<br/>\n";
f+="rate_enabled=The effect happens with a rate of {0} being about {1} %.<br/>\n";
f+="fail=Sufficient conditions of fail\n";
f+="swap_boost=The user and the target switch the levels of the following statistics:<br/>\n";
f+="cancel_chgt_stat=The following statistics of the fighter are now {0}:<br/>\n";
f+="cancel_low_stat=The following lowered statistics of the fighter are now {0}:<br/>\n";
f+="copy_boost=The user copies the levels of the following statistics of the target:<br/>\n";
f+="formula={0}\n";
return f;
}
static String fr(){
String f="effect=Cet effet change des boosts ou des valeurs de statistiques pour au moins un combattant.<br/>\n";
f+="var_statis_rank=Voici le tableau de variations des statistiques de la cible:<br/>\n";
f+="statistic=Statistique\n";
f+="boost=Variation du boost de la statistique\n";
f+="rate_event=Probabilit&eacute;\n";
f+="always_enabled=L''effet a toujours lieu.<br/>\n";
f+="rate_enabled=L''effet a lieu avec une probabilit&eacute; de {0} soit environ de {1} %.<br/>\n";
f+="fail=Conditions suffisantes d''&eacute;chec\n";
f+="swap_boost=Le lanceur et la cible &eacute;changent les boosts des statistiques suivantes:<br/>\n";
f+="cancel_chgt_stat=Les statistiques du combattant suivantes sont remises &agrave; {0}:<br/>\n";
f+="cancel_low_stat=Les statistiques baiss&eacute;es du combattant suivantes sont remises &agrave; {0}:<br/>\n";
f+="copy_boost=Le lanceur copie les boosts des statistiques de la cible suivantes:<br/>\n";
f+="formula={0}\n";
return f;
}
}
