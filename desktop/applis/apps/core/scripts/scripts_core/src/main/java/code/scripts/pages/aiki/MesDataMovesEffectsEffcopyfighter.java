package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffcopyfighter{
private static final String C_P_42_0="The user copies the data of the target: name, height, weight, ability, level, types, base statistics without hp, moves.<br/>\n";
private static final String C_P_42_1="The number of pp by copied move is {0}.<br/>The moves supposed definitively learnt by a copying move are only temporarily learnt.<br/>\n";
private static final String C_P_42_2="Le lanceur copie l''esp&egrave;ce de la cible: nom, taille, masse, capacit&eacute;, niveau, types, statistiques de base sans les pv, attaques.<br/>\n";
private static final String C_P_42_3="Le nombre de pp par attaque copi&eacute;e est de {0}.<br/>Les attaques soit disant d&eacute;finitivement apprises par une attaque de copie ne sont alors que temporairement apprises.<br/>\n";
private static final String M_P_EFFECT="effect";
private static final String M_P_PP_MOVES="pp_moves";
private static final char SEP='=';
private MesDataMovesEffectsEffcopyfighter(){}
static String en(){
String f=M_P_EFFECT+SEP+C_P_42_0;
f+=M_P_PP_MOVES+SEP+C_P_42_1;
return f;
}
static String fr(){
String f=M_P_EFFECT+SEP+C_P_42_2;
f+=M_P_PP_MOVES+SEP+C_P_42_3;
return f;
}
}
