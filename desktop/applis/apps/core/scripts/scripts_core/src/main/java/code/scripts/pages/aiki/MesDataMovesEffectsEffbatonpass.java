package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffbatonpass{
private static final String C_P_39_0="The user forwards the levels of its statistics.<br/>The hp of the clone are not changed.<br/>\n";
private static final String C_P_39_1="The user forwards the effects of the following moves affecting its substitute:<br/>\n";
private static final String C_P_39_2="Le lanceur transmet ses boosts des statistiques.<br/>Les pv du clone sont conserv&eacute;s.<br/>\n";
private static final String C_P_39_3="Le lanceur transmet les effets des attaques, affectant &agrave; son rempla&ccedil;ant, suivantes:<br/>\n";
private static final String M_P_EFFECT="effect";
private static final String M_P_EFFECT_2="effect_2";
private static final char SEP='=';
private MesDataMovesEffectsEffbatonpass(){}
static String en(){
String f=M_P_EFFECT+SEP+C_P_39_0;
f+=M_P_EFFECT_2+SEP+C_P_39_1;
return f;
}
static String fr(){
String f=M_P_EFFECT+SEP+C_P_39_2;
f+=M_P_EFFECT_2+SEP+C_P_39_3;
return f;
}
}
