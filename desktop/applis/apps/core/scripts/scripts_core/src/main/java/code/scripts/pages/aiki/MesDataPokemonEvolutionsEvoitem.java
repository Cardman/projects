package code.scripts.pages.aiki;
final class MesDataPokemonEvolutionsEvoitem{
private static final String C_P_74_0="{0} holds the item <a c:command=\"$clickItem({2})\">{1}</a> by growing a level.\n";
private static final String C_P_74_1="{0} porte l''objet <a c:command=\"$clickItem({2})\">{1}</a> en montant d''un niveau.\n";
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
