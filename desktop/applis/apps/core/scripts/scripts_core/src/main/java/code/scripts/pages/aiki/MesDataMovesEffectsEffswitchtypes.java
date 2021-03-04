package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffswitchtypes{
private MesDataMovesEffectsEffswitchtypes(){}
static String en(){
String f="effect=This effect change the types of a fighter at least.<br/>\n";
f+="affect_types=The target takes the following types:<br/>\n";
f+="affect_types_not_const_user=The user takes the types of the target.<br/>\n";
f+="affect_types_not_const_target=The target takes the types of the user.<br/>\n";
f+="switch_types=The user and the target switch their types.<br/>\n";
f+="res_moves=The user take a random type resisting against the type of the suffered damaging move during the round by changing types.<br/>\n";
f+="user_moves=The user take a random type among the types of its moves by changing types.<br/>\n";
f+="envir=The user take the type in function by the environment of fight:<br/>\n";
f+="envir_env=Environment of fight\n";
f+="envir_type=Type to be affected\n";
f+="envir_env_exc=The types above are not taken if one of the following moves is enabled:<br/>\n";
f+="added_types=The following types are added to the one of the target:<br/>\n";
return f;
}
static String fr(){
String f="effect=Cet effet change les types d''au moins un combattant.<br/>\n";
f+="affect_types=La cible prend les types suivants:<br/>\n";
f+="affect_types_not_const_user=Le lanceur prend les types de la cible.<br/>\n";
f+="affect_types_not_const_target=La cible prend les types du lanceur.<br/>\n";
f+="switch_types=Le lanceur et la cible cible &eacute;changent leurs types.<br/>\n";
f+="res_moves=Le lanceur prend un type al&eacute;atoire r&eacute;sistant au type de l''attaque offensive subie pendant le tour en changeant de type.<br/>\n";
f+="user_moves=Le lanceur prend un type al&eacute;atoire de ses attaques en changeant de type.<br/>\n";
f+="envir=Le lanceur prend le type en fonction de l''environnement de combat:<br/>\n";
f+="envir_env=Environnement de combat\n";
f+="envir_type=Type &agrave; attribuer\n";
f+="envir_env_exc=Les types ci-dessus ne sont pas pris si une des attaques suivantes est active:<br/>\n";
f+="added_types=Les types suivants sont ajout&eacute;s &agrave; ceux de la cible:<br/>\n";
return f;
}
}
