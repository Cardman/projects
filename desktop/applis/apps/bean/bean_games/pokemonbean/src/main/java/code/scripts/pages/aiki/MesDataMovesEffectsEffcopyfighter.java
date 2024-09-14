package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffcopyfighter{
private static final String C_P_42_0="The user copies the data of the target: name, height, weight, ability, level, types, base statistics without hp, moves.\n";
private static final String C_P_42_1="The number of pp by copied move is {0}.The moves supposed definitively learnt by a copying move are only temporarily learnt.\n";
private static final String C_P_42_2="Le lanceur copie l''espèce de la cible: nom, taille, masse, capacité, niveau, types, statistiques de base sans les pv, attaques.\n";
private static final String C_P_42_3="Le nombre de pp par attaque copiée est de {0}.Les attaques soit disant définitivement apprises par une attaque de copie ne sont alors que temporairement apprises.\n";
private static final String M_P_42_EFFECT="effect";
private static final String M_P_42_PP_MOVES="pp_moves";
private static final char SEP='=';
private MesDataMovesEffectsEffcopyfighter(){}
static String en(){
String f=M_P_42_EFFECT+SEP+C_P_42_0;
f+=M_P_42_PP_MOVES+SEP+C_P_42_1;
return f;
}
static String fr(){
String f=M_P_42_EFFECT+SEP+C_P_42_2;
f+=M_P_42_PP_MOVES+SEP+C_P_42_3;
return f;
}
}
