package code.scripts.pages.aiki;
final class MesDataItemsEvostone{
private static final String C_P_20_0="The item allows to evolve the following pokemon by affecting them out of fight:<br/>\n";
private static final String C_P_20_1="L''objet permet de faire &eacute;voluer les pokemons suivants en le leur affectant hors combat:<br/>\n";
private static final String M_P_ITEM="item";
private static final char SEP='=';
private MesDataItemsEvostone(){}
static String en(){
String f=M_P_ITEM+SEP+C_P_20_0;
return f;
}
static String fr(){
String f=M_P_ITEM+SEP+C_P_20_1;
return f;
}
}
