package code.scripts.pages.aiki;
final class MesDataItemsEvoitem{
private static final String C_P_19_0="The item allows to evolve the following pokemon by holding it on a pokemon then by growing one level:<br/>\n";
private static final String C_P_19_1="L''objet permet de faire &eacute;voluer les pokemons suivants en le leur faisant porter et en leur montant d''un niveau:<br/>\n";
private static final String M_P_19_ITEM="item";
private static final char SEP='=';
private MesDataItemsEvoitem(){}
static String en(){
String f=M_P_19_ITEM+SEP+C_P_19_0;
return f;
}
static String fr(){
String f=M_P_19_ITEM+SEP+C_P_19_1;
return f;
}
}
