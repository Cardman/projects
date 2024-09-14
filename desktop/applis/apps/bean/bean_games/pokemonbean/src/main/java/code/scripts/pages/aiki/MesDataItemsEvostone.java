package code.scripts.pages.aiki;
final class MesDataItemsEvostone{
private static final String C_P_20_0="The item allows to evolve the following pokemon by affecting them out of fight:\n";
private static final String C_P_20_1="L''objet permet de faire évoluer les pokemons suivants en le leur affectant hors combat:\n";
private static final String M_P_20_ITEM="item";
private static final char SEP='=';
private MesDataItemsEvostone(){}
static String en(){
return M_P_20_ITEM+SEP+C_P_20_0;
}
static String fr(){
return M_P_20_ITEM+SEP+C_P_20_1;
}
}
