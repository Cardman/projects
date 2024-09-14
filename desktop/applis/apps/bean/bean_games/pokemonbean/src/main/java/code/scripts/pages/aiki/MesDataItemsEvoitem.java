package code.scripts.pages.aiki;
final class MesDataItemsEvoitem{
private static final String C_P_19_0="The item allows to evolve the following pokemon by holding it on a pokemon then by growing one level:\n";
private static final String C_P_19_1="L''objet permet de faire évoluer les pokemons suivants en le leur faisant porter et en leur montant d''un niveau:\n";
private static final String M_P_19_ITEM="item";
private static final char SEP='=';
private MesDataItemsEvoitem(){}
static String en(){
return M_P_19_ITEM+SEP+C_P_19_0;
}
static String fr(){
return M_P_19_ITEM+SEP+C_P_19_1;
}
}
