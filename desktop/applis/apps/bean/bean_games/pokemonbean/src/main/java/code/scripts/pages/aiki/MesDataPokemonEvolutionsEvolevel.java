package code.scripts.pages.aiki;
final class MesDataPokemonEvolutionsEvolevel{
private static final String C_P_75_0="{0} evolves into {1}.\n";
private static final String C_P_75_1="The level of {0} is greater or equals to {1}.\n";
private static final String C_P_75_2="{0} évolue en {1}.\n";
private static final String C_P_75_3="Le niveau de {0} est supérieur ou égal à {1}.\n";
private static final String M_P_75_BASE="base";
private static final String M_P_75_LEVEL="level";
private static final char SEP='=';
private MesDataPokemonEvolutionsEvolevel(){}
static String en(){
String f=M_P_75_BASE+SEP+C_P_75_0;
f+=M_P_75_LEVEL+SEP+C_P_75_1;
return f;
}
static String fr(){
String f=M_P_75_BASE+SEP+C_P_75_2;
f+=M_P_75_LEVEL+SEP+C_P_75_3;
return f;
}
}
