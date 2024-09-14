package code.scripts.pages.aiki;
final class MesDataPokemonEvolutionsEvoitem{
private static final String C_P_74_0="The following item must be hold by {0}, which has to grow a level:\n";
private static final String C_P_74_1="L''objet suivant doit être porté par {0}, qui doit monter d''un niveau:\n";
private static final String M_P_74_ITEM="item";
private static final char SEP='=';
private MesDataPokemonEvolutionsEvoitem(){}
static String en(){
return M_P_74_ITEM+SEP+C_P_74_0;
}
static String fr(){
return M_P_74_ITEM+SEP+C_P_74_1;
}
}
