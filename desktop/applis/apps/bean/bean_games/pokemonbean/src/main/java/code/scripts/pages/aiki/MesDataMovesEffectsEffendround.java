package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffendround{
private static final String C_P_47_0="The rank of the effect is {0}.\n";
private static final String C_P_47_1="See the effect of end of round\n";
private static final String C_P_47_2="The effect is not enabled at the end of round if and only if one of the conditions is checked:\n";
private static final String C_P_47_3="Le rang de l''effet est de {0}.\n";
private static final String C_P_47_4="Voir l''effet de fin de tour\n";
private static final String C_P_47_5="L''effet n''est pas actif en fin de tour si et seulement une des conditions est vérifiée:\n";
private static final String M_P_47_ENDROUND="endRound";
private static final String M_P_47_RANK="rank";
private static final String M_P_47_REASONS="reasons";
private static final char SEP='=';
private MesDataMovesEffectsEffendround(){}
static String en(){
String f=M_P_47_RANK+SEP+C_P_47_0;
f+=M_P_47_ENDROUND+SEP+C_P_47_1;
f+=M_P_47_REASONS+SEP+C_P_47_2;
return f;
}
static String fr(){
String f=M_P_47_RANK+SEP+C_P_47_3;
f+=M_P_47_ENDROUND+SEP+C_P_47_4;
f+=M_P_47_REASONS+SEP+C_P_47_5;
return f;
}
}
