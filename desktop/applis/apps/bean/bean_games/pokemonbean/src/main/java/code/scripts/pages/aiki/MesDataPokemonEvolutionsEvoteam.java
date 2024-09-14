package code.scripts.pages.aiki;
final class MesDataPokemonEvolutionsEvoteam{
private static final String C_P_80_0="The following pokemon must be present in the team while {0} grows a level:\n";
private static final String C_P_80_1="Le pokemon suivant doit être présent dans l''équipe pendant que {0} monte d''un niveau:\n";
private static final String M_P_80_TEAM="team";
private static final char SEP='=';
private MesDataPokemonEvolutionsEvoteam(){}
static String en(){
return M_P_80_TEAM+SEP+C_P_80_0;
}
static String fr(){
return M_P_80_TEAM+SEP+C_P_80_1;
}
}
