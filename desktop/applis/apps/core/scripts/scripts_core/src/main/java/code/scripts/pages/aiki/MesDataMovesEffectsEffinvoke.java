package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffinvoke{
private MesDataMovesEffectsEffinvoke(){}
static String en(){
String f="effect=The effect of the move invokes an other move not invoked while the round of the user.<br/>While the move has an effect that invokes a not already invoked move, the moves are consecutively invoked.<br/>\n";
f+="invoke_move_but_user=The user invokes a not invoked move that the user does not know currently.<br/>\n";
f+="invoke_target_chosen_move=The user invokes the chosen move by the target.<br/>\n";
f+="invoke_user_move_while_sleep=The asleep user invokes a not invoked move that the user knows currently.<br/>\n";
f+="invoke_move_part=The user invokes a move of one of its partners that the user does not know currently.<br/>\n";
f+="invoke_move_success_target=The user invokes the last successful move by the target while the fight.<br/>\n";
f+="invoke_suffered_move=The user invokes the last move that he is suffered while the fight.<br/>\n";
f+="rate_invoke_move=Damage inflicted by the invoked move are multiplied by {0}.<br/>\n";
f+="moves_not_invoked=The following moves cannot be invoked:<br/>\n";
f+="move_fct_env=Here is the table of the invoked moves in function by the environment type of fight:<br/>\n";
f+="move_fct_env_exc=The above moves are not invoked if one of the following moves is enabled:<br/>\n";
f+="env_type=Type of environment\n";
f+="invoked_move=Invoked move\n";
f+="invoke_move_type=Here is le table of the invoked moves in function by owned types by the user:<br/>\n";
f+="owned_type=Owned Type(s)\n";
f+="other_owned_type=Other owned type\n";
return f;
}
static String fr(){
String f="effect=L''effet de l''attaque invoque une autre attaque non invoqu&eacute;e dans le tour du lanceur.<br/>Tant que l''attaque poss&egrave;de un effet qui invoque une attaque pas encore invoqu&eacute;e, les attaques sont successivement invoqu&eacute;es.<br/>\n";
f+="invoke_move_but_user=Le lanceur invoque une attaque non invoqu&eacute;e qu''il ne conna&icirc;t pas actuellement.<br/>\n";
f+="invoke_target_chosen_move=Le lanceur invoque l''attaque choisie par la cible.<br/>\n";
f+="invoke_user_move_while_sleep=Le lanceur endormi invoque une attaque non invoqu&eacute;e qu''il conna&icirc;t actuellement.<br/>\n";
f+="invoke_move_part=Le lanceur invoque une attaque d''un de ses partenaire qu''il ne conna&icirc;t pas actuellement.<br/>\n";
f+="invoke_move_success_target=Le lanceur invoque la derni&egrave;re attaque r&eacute;ussie par la cible pendant le combat.<br/>\n";
f+="invoke_suffered_move=Le lanceur invoque la derni&egrave;re attaque qu''il a subie pendant le combat.<br/>\n";
f+="rate_invoke_move=Les d&eacute;g&acirc;ts caus&eacute;s par l''attaque invoqu&eacute;e sont multipli&eacute;s par {0}.<br/>\n";
f+="moves_not_invoked=Les attaques suivantes ne peuvent pas &ecirc;tre invoqu&eacute;es:<br/>\n";
f+="move_fct_env=Voici le tableau des attaques invoqu&eacute;es en fonction du type d''environnement de combat:<br/>\n";
f+="move_fct_env_exc=Les attaques ci-dessus ne sont pas invoqu&eacute;es si une des attaques suivantes est active:<br/>\n";
f+="env_type=Type d''environnement\n";
f+="invoked_move=Attaque invoqu&eacute;e\n";
f+="invoke_move_type=Voici le tableau des attaques invoqu&eacute;es en fonction des types poss&eacute;d&eacute;s par le lanceur:<br/>\n";
f+="owned_type=Type(s) poss&eacute;d&eacute;(s)\n";
f+="other_owned_type=Autre type poss&eacute;d&eacute;\n";
return f;
}
}
