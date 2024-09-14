package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffswitchtypes{
private static final String C_P_65_0="This effect change the types of a fighter at least.\n";
private static final String C_P_65_10="The types above are not taken if one of the following moves is enabled:\n";
private static final String C_P_65_11="The following types are added to the one of the target:\n";
private static final String C_P_65_12="Cet effet change les types d''au moins un combattant.\n";
private static final String C_P_65_13="La cible prend les types suivants:\n";
private static final String C_P_65_14="Le lanceur prend les types de la cible.\n";
private static final String C_P_65_15="La cible prend les types du lanceur.\n";
private static final String C_P_65_16="Le lanceur et la cible cible échangent leurs types.\n";
private static final String C_P_65_17="Le lanceur prend un type aléatoire résistant au type de l''attaque offensive subie pendant le tour en changeant de type.\n";
private static final String C_P_65_18="Le lanceur prend un type aléatoire de ses attaques en changeant de type.\n";
private static final String C_P_65_19="Le lanceur prend le type en fonction de l''environnement de combat:\n";
private static final String C_P_65_1="The target takes the following types:\n";
private static final String C_P_65_20="Environnement de combat\n";
private static final String C_P_65_21="Type à attribuer\n";
private static final String C_P_65_22="Les types ci-dessus ne sont pas pris si une des attaques suivantes est active:\n";
private static final String C_P_65_23="Les types suivants sont ajoutés à ceux de la cible:\n";
private static final String C_P_65_2="The user takes the types of the target.\n";
private static final String C_P_65_3="The target takes the types of the user.\n";
private static final String C_P_65_4="The user and the target switch their types.\n";
private static final String C_P_65_5="The user take a random type resisting against the type of the suffered damaging move during the round by changing types.\n";
private static final String C_P_65_6="The user take a random type among the types of its moves by changing types.\n";
private static final String C_P_65_7="The user take the type in function by the environment of fight:\n";
private static final String C_P_65_8="Environment of fight\n";
private static final String C_P_65_9="Type to be affected\n";
private static final String M_P_65_ADDED_TYPES="added_types";
private static final String M_P_65_AFFECT_TYPES="affect_types";
private static final String M_P_65_AFFECT_TYPES_NOT_CONST_TARGET="affect_types_not_const_target";
private static final String M_P_65_AFFECT_TYPES_NOT_CONST_USER="affect_types_not_const_user";
private static final String M_P_65_EFFECT="effect";
private static final String M_P_65_ENVIR="envir";
private static final String M_P_65_ENVIR_ENV="envir_env";
private static final String M_P_65_ENVIR_ENV_EXC="envir_env_exc";
private static final String M_P_65_ENVIR_TYPE="envir_type";
private static final String M_P_65_RES_MOVES="res_moves";
private static final String M_P_65_SWITCH_TYPES="switch_types";
private static final String M_P_65_USER_MOVES="user_moves";
private static final char SEP='=';
private MesDataMovesEffectsEffswitchtypes(){}
static String en(){
String f=M_P_65_EFFECT+SEP+C_P_65_0;
f+=M_P_65_AFFECT_TYPES+SEP+C_P_65_1;
f+=M_P_65_AFFECT_TYPES_NOT_CONST_USER+SEP+C_P_65_2;
f+=M_P_65_AFFECT_TYPES_NOT_CONST_TARGET+SEP+C_P_65_3;
f+=M_P_65_SWITCH_TYPES+SEP+C_P_65_4;
f+=M_P_65_RES_MOVES+SEP+C_P_65_5;
f+=M_P_65_USER_MOVES+SEP+C_P_65_6;
f+=M_P_65_ENVIR+SEP+C_P_65_7;
f+=M_P_65_ENVIR_ENV+SEP+C_P_65_8;
f+=M_P_65_ENVIR_TYPE+SEP+C_P_65_9;
f+=M_P_65_ENVIR_ENV_EXC+SEP+C_P_65_10;
f+=M_P_65_ADDED_TYPES+SEP+C_P_65_11;
return f;
}
static String fr(){
String f=M_P_65_EFFECT+SEP+C_P_65_12;
f+=M_P_65_AFFECT_TYPES+SEP+C_P_65_13;
f+=M_P_65_AFFECT_TYPES_NOT_CONST_USER+SEP+C_P_65_14;
f+=M_P_65_AFFECT_TYPES_NOT_CONST_TARGET+SEP+C_P_65_15;
f+=M_P_65_SWITCH_TYPES+SEP+C_P_65_16;
f+=M_P_65_RES_MOVES+SEP+C_P_65_17;
f+=M_P_65_USER_MOVES+SEP+C_P_65_18;
f+=M_P_65_ENVIR+SEP+C_P_65_19;
f+=M_P_65_ENVIR_ENV+SEP+C_P_65_20;
f+=M_P_65_ENVIR_TYPE+SEP+C_P_65_21;
f+=M_P_65_ENVIR_ENV_EXC+SEP+C_P_65_22;
f+=M_P_65_ADDED_TYPES+SEP+C_P_65_23;
return f;
}
}
