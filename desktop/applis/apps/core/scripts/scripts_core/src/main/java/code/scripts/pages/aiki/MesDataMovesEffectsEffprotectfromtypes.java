package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffprotectfromtypes{
private static final String C_P_54_0="This effect protects a fighter against a type of move.<br/>\n";
private static final String C_P_54_1="The target is protected against moves of the following types:<br/>\n";
private static final String C_P_54_2="Cette effet immunise un combattant &agrave; un type d''attaque.<br/>\n";
private static final String C_P_54_3="La cible est immunis&eacute;e aux attaques de types suivants:<br/>\n";
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
