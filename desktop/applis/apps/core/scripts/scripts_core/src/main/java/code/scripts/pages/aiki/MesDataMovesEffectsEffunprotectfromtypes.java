package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffunprotectfromtypes{
private MesDataMovesEffectsEffunprotectfromtypes(){}
static String en(){
String f="effect=By disabling some protections of the target, the effect allows the user to use moves that the target was protected.<br/>\n";
f+="attack_target_types=The target can be affected by moves with one of the following types:<br/>\n";
f+="disable_immu_types=The target is not protected anymore against the moves aux attaques with one of the following types:<br/>\n";
f+="disable_immu_from_moves=The moves, whose effects protecting the target against a type are stopped, are the following one:<br/>\n";
f+="types=Here is the list of zero duals damaging type - defending type affected to 1:<br/>\n";
f+="types_damag=Type of the move\n";
f+="types_pk=Type of the targetted pokemon\n";
return f;
}
static String fr(){
String f="effect=En d&eacute;sactivant certaines immunit&eacute;s de la cible, l''effet permet au lanceur d''utiliser des attaques auxquelles la cible &eacute;tait immunis&eacute;e.<br/>\n";
f+="attack_target_types=La cible peut &ecirc;tre affect&eacute;e par des attaques d''un des types suivants:<br/>\n";
f+="disable_immu_types=La cible n''est plus immunis&eacute;es aux attaques d''un des types suivants:<br/>\n";
f+="disable_immu_from_moves=Les attaques dont les effets immunisant la cible &agrave; un type sont stopp&eacute;s sont les suivantes:<br/>\n";
f+="types=Voici la liste des couples type offensif - type d&eacute;fensif nuls passant &agrave; 1:<br/>\n";
f+="types_damag=Type de l''attaque\n";
f+="types_pk=Type du pokemon attaqu&eacute;\n";
return f;
}
}
