package code.scripts.pages.aiki;
final class MesDataPokemonEvolutionsEvolevelgender{
private static final String C_P_76_0="{0} evolves into {1}.\n";
private static final String C_P_76_1="The level of {0} is greater or equals to {1} and the gender of {0} is {2}.\n";
private static final String C_P_76_2="{0} évolue en {1}.\n";
private static final String C_P_76_3="Le niveau de {0} est supérieur ou égal à {1} et le genre de {0} est {2}.\n";
private static final String M_P_76_BASE="base";
private static final String M_P_76_GENDER="gender";
private static final char SEP='=';
private MesDataPokemonEvolutionsEvolevelgender(){}
static String en(){
String f=M_P_76_BASE+SEP+C_P_76_0;
f+=M_P_76_GENDER+SEP+C_P_76_1;
return f;
}
static String fr(){
String f=M_P_76_BASE+SEP+C_P_76_2;
f+=M_P_76_GENDER+SEP+C_P_76_3;
return f;
}
}
