package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffinvoke{
private static final String C_P_50_0="The effect of the move invokes an other move not invoked while the round of the user.<br/>While the move has an effect that invokes a not already invoked move, the moves are consecutively invoked.<br/>\n";
private static final String C_P_50_10="The above moves are not invoked if one of the following moves is enabled:<br/>\n";
private static final String C_P_50_11="Type of environment\n";
private static final String C_P_50_12="Invoked move\n";
private static final String C_P_50_13="Here is le table of the invoked moves in function by owned types by the user:<br/>\n";
private static final String C_P_50_14="Owned Type(s)\n";
private static final String C_P_50_15="Other owned type\n";
private static final String C_P_50_16="L''effet de l''attaque invoque une autre attaque non invoqu&eacute;e dans le tour du lanceur.<br/>Tant que l''attaque poss&egrave;de un effet qui invoque une attaque pas encore invoqu&eacute;e, les attaques sont successivement invoqu&eacute;es.<br/>\n";
private static final String C_P_50_17="Le lanceur invoque une attaque non invoqu&eacute;e qu''il ne conna&icirc;t pas actuellement.<br/>\n";
private static final String C_P_50_18="Le lanceur invoque l''attaque choisie par la cible.<br/>\n";
private static final String C_P_50_19="Le lanceur endormi invoque une attaque non invoqu&eacute;e qu''il conna&icirc;t actuellement.<br/>\n";
private static final String C_P_50_1="The user invokes a not invoked move that the user does not know currently.<br/>\n";
private static final String C_P_50_20="Le lanceur invoque une attaque d''un de ses partenaire qu''il ne conna&icirc;t pas actuellement.<br/>\n";
private static final String C_P_50_21="Le lanceur invoque la derni&egrave;re attaque r&eacute;ussie par la cible pendant le combat.<br/>\n";
private static final String C_P_50_22="Le lanceur invoque la derni&egrave;re attaque qu''il a subie pendant le combat.<br/>\n";
private static final String C_P_50_23="Les d&eacute;g&acirc;ts caus&eacute;s par l''attaque invoqu&eacute;e sont multipli&eacute;s par {0}.<br/>\n";
private static final String C_P_50_24="Les attaques suivantes ne peuvent pas &ecirc;tre invoqu&eacute;es:<br/>\n";
private static final String C_P_50_25="Voici le tableau des attaques invoqu&eacute;es en fonction du type d''environnement de combat:<br/>\n";
private static final String C_P_50_26="Les attaques ci-dessus ne sont pas invoqu&eacute;es si une des attaques suivantes est active:<br/>\n";
private static final String C_P_50_27="Type d''environnement\n";
private static final String C_P_50_28="Attaque invoqu&eacute;e\n";
private static final String C_P_50_29="Voici le tableau des attaques invoqu&eacute;es en fonction des types poss&eacute;d&eacute;s par le lanceur:<br/>\n";
private static final String C_P_50_2="The user invokes the chosen move by the target.<br/>\n";
private static final String C_P_50_30="Type(s) poss&eacute;d&eacute;(s)\n";
private static final String C_P_50_31="Autre type poss&eacute;d&eacute;\n";
private static final String C_P_50_3="The asleep user invokes a not invoked move that the user knows currently.<br/>\n";
private static final String C_P_50_4="The user invokes a move of one of its partners that the user does not know currently.<br/>\n";
private static final String C_P_50_5="The user invokes the last successful move by the target while the fight.<br/>\n";
private static final String C_P_50_6="The user invokes the last move that he is suffered while the fight.<br/>\n";
private static final String C_P_50_7="Damage inflicted by the invoked move are multiplied by {0}.<br/>\n";
private static final String C_P_50_8="The following moves cannot be invoked:<br/>\n";
private static final String C_P_50_9="Here is the table of the invoked moves in function by the environment type of fight:<br/>\n";
private static final String M_P_EFFECT="effect";
private static final String M_P_ENV_TYPE="env_type";
private static final String M_P_INVOKED_MOVE="invoked_move";
private static final String M_P_INVOKE_MOVE_BUT_USER="invoke_move_but_user";
private static final String M_P_INVOKE_MOVE_PART="invoke_move_part";
private static final String M_P_INVOKE_MOVE_SUCCESS_TARGET="invoke_move_success_target";
private static final String M_P_INVOKE_MOVE_TYPE="invoke_move_type";
private static final String M_P_INVOKE_SUFFERED_MOVE="invoke_suffered_move";
private static final String M_P_INVOKE_TARGET_CHOSEN_MOVE="invoke_target_chosen_move";
private static final String M_P_INVOKE_USER_MOVE_WHILE_SLEEP="invoke_user_move_while_sleep";
private static final String M_P_MOVES_NOT_INVOKED="moves_not_invoked";
private static final String M_P_MOVE_FCT_ENV="move_fct_env";
private static final String M_P_MOVE_FCT_ENV_EXC="move_fct_env_exc";
private static final String M_P_OTHER_OWNED_TYPE="other_owned_type";
private static final String M_P_OWNED_TYPE="owned_type";
private static final String M_P_RATE_INVOKE_MOVE="rate_invoke_move";
private static final char SEP='=';
private MesDataMovesEffectsEffinvoke(){}
static String en(){
String f=M_P_EFFECT+SEP+C_P_50_0;
f+=M_P_INVOKE_MOVE_BUT_USER+SEP+C_P_50_1;
f+=M_P_INVOKE_TARGET_CHOSEN_MOVE+SEP+C_P_50_2;
f+=M_P_INVOKE_USER_MOVE_WHILE_SLEEP+SEP+C_P_50_3;
f+=M_P_INVOKE_MOVE_PART+SEP+C_P_50_4;
f+=M_P_INVOKE_MOVE_SUCCESS_TARGET+SEP+C_P_50_5;
f+=M_P_INVOKE_SUFFERED_MOVE+SEP+C_P_50_6;
f+=M_P_RATE_INVOKE_MOVE+SEP+C_P_50_7;
f+=M_P_MOVES_NOT_INVOKED+SEP+C_P_50_8;
f+=M_P_MOVE_FCT_ENV+SEP+C_P_50_9;
f+=M_P_MOVE_FCT_ENV_EXC+SEP+C_P_50_10;
f+=M_P_ENV_TYPE+SEP+C_P_50_11;
f+=M_P_INVOKED_MOVE+SEP+C_P_50_12;
f+=M_P_INVOKE_MOVE_TYPE+SEP+C_P_50_13;
f+=M_P_OWNED_TYPE+SEP+C_P_50_14;
f+=M_P_OTHER_OWNED_TYPE+SEP+C_P_50_15;
return f;
}
static String fr(){
String f=M_P_EFFECT+SEP+C_P_50_16;
f+=M_P_INVOKE_MOVE_BUT_USER+SEP+C_P_50_17;
f+=M_P_INVOKE_TARGET_CHOSEN_MOVE+SEP+C_P_50_18;
f+=M_P_INVOKE_USER_MOVE_WHILE_SLEEP+SEP+C_P_50_19;
f+=M_P_INVOKE_MOVE_PART+SEP+C_P_50_20;
f+=M_P_INVOKE_MOVE_SUCCESS_TARGET+SEP+C_P_50_21;
f+=M_P_INVOKE_SUFFERED_MOVE+SEP+C_P_50_22;
f+=M_P_RATE_INVOKE_MOVE+SEP+C_P_50_23;
f+=M_P_MOVES_NOT_INVOKED+SEP+C_P_50_24;
f+=M_P_MOVE_FCT_ENV+SEP+C_P_50_25;
f+=M_P_MOVE_FCT_ENV_EXC+SEP+C_P_50_26;
f+=M_P_ENV_TYPE+SEP+C_P_50_27;
f+=M_P_INVOKED_MOVE+SEP+C_P_50_28;
f+=M_P_INVOKE_MOVE_TYPE+SEP+C_P_50_29;
f+=M_P_OWNED_TYPE+SEP+C_P_50_30;
f+=M_P_OTHER_OWNED_TYPE+SEP+C_P_50_31;
return f;
}
}
