package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffcounterattack{
private MesDataMovesEffectsEffcounterattack(){}
static String en(){
String f="effect=The effect protects its user against some moves and can let its user counter attack (return some effects of these moves).<br/>\n";
f+="fail_protect=While a pokemon uses a move against the user of the move {0}, the protection of the effect fails if and only if one of the conditions is checked:<br/>\n";
f+="counter_protect=While a pokemon uses a move against the user of the move {0}, the counter attack of the effect fails if and only if one of the conditions is checked:<br/>\n";
f+="suffering_types=While a pokemon uses a move against the user of the move {0}, it loose health points in function by the types of the used move (damage is added if there is some types for the move):<br/>\n";
f+="suffering_types_t=Type of the move\n";
f+="suffering_types_hp=Rate of lost life\n";
f+="dropped_stat=While a pokemon uses a move against the user of the move {0} with a direct move, the levels of its statistics vary by the following way:<br/>\n";
f+="dropped_stat_s=Statistic\n";
f+="dropped_stat_v=Variation of the statistic\n";
f+="suffering_direct=While a pokemon uses a move against the user of the move {0} with a direct move, the rate of lost life is {1}.<br/>\n";
f+="formula={0}\n";
return f;
}
static String fr(){
String f="effect=L''effet prot&egrave;ge son lanceur contre certains attaques et peut permettre de contre attaquer (retourner certains des effets de ces attaques).<br/>\n";
f+="fail_protect=Lorsqu''un pokemon attaque le lanceur de l''attaque {0}, la protection de l''effet &eacute;choue si et seulement si une des conditions est v&eacute;rifi&eacute;e:<br/>\n";
f+="counter_protect=Lorsqu''un pokemon adverse attaque le lanceur de l''attaque {0}, la contre-attaque de l''effet &eacute;choue si et seulement si une des conditions est v&eacute;rifi&eacute;e:<br/>\n";
f+="suffering_types=Lorsqu''un pokemon adverse attaque le lanceur de l''attaque {0}, il perd des d&eacute;g&acirc;ts en fonction des types de l''attaque utilis&eacute;e (les degats sont cumules s''il y a plusieurs types pour l''attaque):<br/>\n";
f+="suffering_types_t=Type de l''attaque\n";
f+="suffering_types_hp=Taux de vie perdue\n";
f+="dropped_stat=Lorsqu''un pokemon adverse attaque le lanceur de l''attaque {0} avec une attaque directe, ses boosts de statistiques varient de la fa&ccedil;on suivante:<br/>\n";
f+="dropped_stat_s=Statistique\n";
f+="dropped_stat_v=Variation de la statistique\n";
f+="suffering_direct=Lorsqu''un pokemon adverse attaque le lanceur de l''attaque {0} avec une attaque directe, le taux de vie perdue est de {1}.<br/>\n";
f+="formula={0}\n";
return f;
}
}
