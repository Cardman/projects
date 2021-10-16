package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffrestriction{
private static final String C_P_57_0="This effect disables the items of its targets.<br/>\n";
private static final String C_P_57_10="Cette effet interdit &agrave; ses cibles d''utiliser certaines attaques.<br/>\n";
private static final String C_P_57_11="Le combattant ne peut plus utiliser les attaques non offensives.<br/>\n";
private static final String C_P_57_12="Le combattant ne peut pas choisir cons&eacute;cutivement la m&ecirc;me attaque. Mais, il garde la possibilit&eacute; d''utiliser un tour sur deux.<br/>\n";
private static final String C_P_57_13="La cible ne peut pas utiliser les attaques que le lanceur poss&egrave;de.<br/>\n";
private static final String C_P_57_14="Le combattant ne peut pas r&eacute;utiliser l''attaque venant d''&ecirc;tre choisie pendant un certain nombre de tour.<br/>\n";
private static final String C_P_57_15="Le combattant doit r&eacute;utiliser l''attaque venant d''&ecirc;tre choisie pendant un certain nombre de tour.<br/>\n";
private static final String C_P_57_1="The item of the fighter has not effect anymore during some rounds.<br/>\n";
private static final String C_P_57_2="This effect forbids its targets to use some moves.<br/>\n";
private static final String C_P_57_3="The fighter cannot use not damaging moves anymore.<br/>\n";
private static final String C_P_57_4="The fighter cannot choose consecutively the same move. But, the fighter keeps the possibility to use one round over two.<br/>\n";
private static final String C_P_57_5="The target cannot use the moves that the user owns.<br/>\n";
private static final String C_P_57_6="The fighter cannot reuse the move having just been chosen during some rounds.<br/>\n";
private static final String C_P_57_7="The fighter must reuse the move having just been chosen during some rounds.<br/>\n";
private static final String C_P_57_8="Cette effet d&eacute;sactive les objets de ses cibles.<br/>\n";
private static final String C_P_57_9="L''objet du combattant n''a plus d''effet pendant un certain nombre de tour.<br/>\n";
private static final String M_P_EFFECT_ITEM="effect_item";
private static final String M_P_EFFECT_ITEM_2="effect_item_2";
private static final String M_P_EFFECT_MOVE="effect_move";
private static final String M_P_FORBID_LAST_MOVE="forbid_last_move";
private static final String M_P_FORBID_STATUS_MOVE="forbid_status_move";
private static final String M_P_FORBID_USER_MOVES="forbid_user_moves";
private static final String M_P_FORBID_USE_LAST_MOVE="forbid_use_last_move";
private static final String M_P_FORCE_USE_LAST_MOVE="force_use_last_move";
private static final char SEP='=';
private MesDataMovesEffectsEffrestriction(){}
static String en(){
String f=M_P_EFFECT_ITEM+SEP+C_P_57_0;
f+=M_P_EFFECT_ITEM_2+SEP+C_P_57_1;
f+=M_P_EFFECT_MOVE+SEP+C_P_57_2;
f+=M_P_FORBID_STATUS_MOVE+SEP+C_P_57_3;
f+=M_P_FORBID_LAST_MOVE+SEP+C_P_57_4;
f+=M_P_FORBID_USER_MOVES+SEP+C_P_57_5;
f+=M_P_FORBID_USE_LAST_MOVE+SEP+C_P_57_6;
f+=M_P_FORCE_USE_LAST_MOVE+SEP+C_P_57_7;
return f;
}
static String fr(){
String f=M_P_EFFECT_ITEM+SEP+C_P_57_8;
f+=M_P_EFFECT_ITEM_2+SEP+C_P_57_9;
f+=M_P_EFFECT_MOVE+SEP+C_P_57_10;
f+=M_P_FORBID_STATUS_MOVE+SEP+C_P_57_11;
f+=M_P_FORBID_LAST_MOVE+SEP+C_P_57_12;
f+=M_P_FORBID_USER_MOVES+SEP+C_P_57_13;
f+=M_P_FORBID_USE_LAST_MOVE+SEP+C_P_57_14;
f+=M_P_FORCE_USE_LAST_MOVE+SEP+C_P_57_15;
return f;
}
}
