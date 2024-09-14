package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffprotectfromtypes{
private static final String C_P_54_0="This effect protects a fighter against a type of move.\n";
private static final String C_P_54_1="The target is protected against moves of the following types:\n";
private static final String C_P_54_2="Cette effet immunise un combattant à un type d''attaque.\n";
private static final String C_P_54_3="La cible est immunisée aux attaques de types suivants:\n";
private static final String M_P_54_EFFECT="effect";
private static final String M_P_54_IMMU_MOVE_TYPES="immu_move_types";
private static final char SEP='=';
private MesDataMovesEffectsEffprotectfromtypes(){}
static String en(){
String f=M_P_54_EFFECT+SEP+C_P_54_0;
f+=M_P_54_IMMU_MOVE_TYPES+SEP+C_P_54_1;
return f;
}
static String fr(){
String f=M_P_54_EFFECT+SEP+C_P_54_2;
f+=M_P_54_IMMU_MOVE_TYPES+SEP+C_P_54_3;
return f;
}
}
