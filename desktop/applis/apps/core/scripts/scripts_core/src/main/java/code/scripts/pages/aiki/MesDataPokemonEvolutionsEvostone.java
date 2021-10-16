package code.scripts.pages.aiki;
final class MesDataPokemonEvolutionsEvostone{
private static final String C_P_78_0="The item <a c:command=\"$clickStone({2})\">{1}</a> must be used on {0}.\n";
private static final String C_P_78_1="L''objet <a c:command=\"$clickStone({2})\">{1}</a> doit &ecirc;tre utilis&eacute; sur {0}.\n";
private static final String M_P_78_STONE="stone";
private static final char SEP='=';
private MesDataPokemonEvolutionsEvostone(){}
static String en(){
return M_P_78_STONE+SEP+C_P_78_0;
}
static String fr(){
return M_P_78_STONE+SEP+C_P_78_1;
}
}
