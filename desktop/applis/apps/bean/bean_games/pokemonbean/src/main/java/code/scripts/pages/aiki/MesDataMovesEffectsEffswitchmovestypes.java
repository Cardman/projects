package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffswitchmovestypes{
private static final String C_P_62_0="The effect changes the types of a move of a target not having acted yet.\n";
private static final String C_P_62_10="Ancien type\n";
private static final String C_P_62_11="Nouveau type\n";
private static final String C_P_62_1="The types of the move are replaced by the following types:\n";
private static final String C_P_62_2="The types of the move are changed in the following way after possible replacement of types:\n";
private static final String C_P_62_3="The types of the move are changed in the following way:\n";
private static final String C_P_62_4="Old type\n";
private static final String C_P_62_5="New type\n";
private static final String C_P_62_6="L''effet change les types d''une attaque d''une cible n''ayant pas encore joué.\n";
private static final String C_P_62_7="Les types de l''attaque sont remplacés par les types suivants:\n";
private static final String C_P_62_8="Les types de l''attaque sont convertis de la manière suivante aprè éventuel remplacement de types:\n";
private static final String C_P_62_9="Les types de l''attaque sont convertis de la manière suivante:\n";
private static final String M_P_62_CHANGING_TYPE="changing_type";
private static final String M_P_62_CHANGING_TYPE_POSSIBLE="changing_type_possible";
private static final String M_P_62_EFFECT="effect";
private static final String M_P_62_NEW_TYPE="new_type";
private static final String M_P_62_OLD_TYPE="old_type";
private static final String M_P_62_REPLACING_TYPES="replacing_types";
private static final char SEP='=';
private MesDataMovesEffectsEffswitchmovestypes(){}
static String en(){
String f=M_P_62_EFFECT+SEP+C_P_62_0;
f+=M_P_62_REPLACING_TYPES+SEP+C_P_62_1;
f+=M_P_62_CHANGING_TYPE_POSSIBLE+SEP+C_P_62_2;
f+=M_P_62_CHANGING_TYPE+SEP+C_P_62_3;
f+=M_P_62_OLD_TYPE+SEP+C_P_62_4;
f+=M_P_62_NEW_TYPE+SEP+C_P_62_5;
return f;
}
static String fr(){
String f=M_P_62_EFFECT+SEP+C_P_62_6;
f+=M_P_62_REPLACING_TYPES+SEP+C_P_62_7;
f+=M_P_62_CHANGING_TYPE_POSSIBLE+SEP+C_P_62_8;
f+=M_P_62_CHANGING_TYPE+SEP+C_P_62_9;
f+=M_P_62_OLD_TYPE+SEP+C_P_62_10;
f+=M_P_62_NEW_TYPE+SEP+C_P_62_11;
return f;
}
}
