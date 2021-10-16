package code.scripts.pages.aiki;
final class MesDataPokemonEvolutionsEvostonegender{
private static final String C_P_79_0="The item <a c:command=\"$clickStone({3})\">{1}</a> must be used on {0}. The gender of {0} is {2}.\n";
private static final String C_P_79_1="L''objet <a c:command=\"$clickStone({3})\">{1}</a> doit &ecirc;tre utilis&eacute; sur {0}. Le genre de {0} est {2}.\n";
private static final String M_P_STONE_GENDER="stone_gender";
private static final char SEP='=';
private MesDataPokemonEvolutionsEvostonegender(){}
static String en(){
String f=M_P_STONE_GENDER+SEP+C_P_79_0;
return f;
}
static String fr(){
String f=M_P_STONE_GENDER+SEP+C_P_79_1;
return f;
}
}
