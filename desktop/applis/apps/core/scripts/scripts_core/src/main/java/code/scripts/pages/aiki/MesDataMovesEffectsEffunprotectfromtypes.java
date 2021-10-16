package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffunprotectfromtypes{
private static final String C_P_68_0="By disabling some protections of the target, the effect allows the user to use moves that the target was protected.<br/>\n";
private static final String C_P_68_10="Les attaques dont les effets immunisant la cible &agrave; un type sont stopp&eacute;s sont les suivantes:<br/>\n";
private static final String C_P_68_11="Voici la liste des couples type offensif - type d&eacute;fensif nuls passant &agrave; 1:<br/>\n";
private static final String C_P_68_12="Type de l''attaque\n";
private static final String C_P_68_13="Type du pokemon attaqu&eacute;\n";
private static final String C_P_68_1="The target can be affected by moves with one of the following types:<br/>\n";
private static final String C_P_68_2="The target is not protected anymore against the moves aux attaques with one of the following types:<br/>\n";
private static final String C_P_68_3="The moves, whose effects protecting the target against a type are stopped, are the following one:<br/>\n";
private static final String C_P_68_4="Here is the list of zero duals damaging type - defending type affected to 1:<br/>\n";
private static final String C_P_68_5="Type of the move\n";
private static final String C_P_68_6="Type of the targetted pokemon\n";
private static final String C_P_68_7="En d&eacute;sactivant certaines immunit&eacute;s de la cible, l''effet permet au lanceur d''utiliser des attaques auxquelles la cible &eacute;tait immunis&eacute;e.<br/>\n";
private static final String C_P_68_8="La cible peut &ecirc;tre affect&eacute;e par des attaques d''un des types suivants:<br/>\n";
private static final String C_P_68_9="La cible n''est plus immunis&eacute;es aux attaques d''un des types suivants:<br/>\n";
private static final String M_P_ATTACK_TARGET_TYPES="attack_target_types";
private static final String M_P_DISABLE_IMMU_FROM_MOVES="disable_immu_from_moves";
private static final String M_P_DISABLE_IMMU_TYPES="disable_immu_types";
private static final String M_P_EFFECT="effect";
private static final String M_P_TYPES="types";
private static final String M_P_TYPES_DAMAG="types_damag";
private static final String M_P_TYPES_PK="types_pk";
private static final char SEP='=';
private MesDataMovesEffectsEffunprotectfromtypes(){}
static String en(){
String f=M_P_EFFECT+SEP+C_P_68_0;
f+=M_P_ATTACK_TARGET_TYPES+SEP+C_P_68_1;
f+=M_P_DISABLE_IMMU_TYPES+SEP+C_P_68_2;
f+=M_P_DISABLE_IMMU_FROM_MOVES+SEP+C_P_68_3;
f+=M_P_TYPES+SEP+C_P_68_4;
f+=M_P_TYPES_DAMAG+SEP+C_P_68_5;
f+=M_P_TYPES_PK+SEP+C_P_68_6;
return f;
}
static String fr(){
String f=M_P_EFFECT+SEP+C_P_68_7;
f+=M_P_ATTACK_TARGET_TYPES+SEP+C_P_68_8;
f+=M_P_DISABLE_IMMU_TYPES+SEP+C_P_68_9;
f+=M_P_DISABLE_IMMU_FROM_MOVES+SEP+C_P_68_10;
f+=M_P_TYPES+SEP+C_P_68_11;
f+=M_P_TYPES_DAMAG+SEP+C_P_68_12;
f+=M_P_TYPES_PK+SEP+C_P_68_13;
return f;
}
}
