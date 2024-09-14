package code.scripts.pages.aiki;
final class MesDataPokemonEvolutionsEvohappiness{
private static final String C_P_73_0="{0} evolves into {1}.\n";
private static final String C_P_73_1="The happiness of {0} is greater or equals to {1} by growing a level.\n";
private static final String C_P_73_2="{0} évolue en {1}.\n";
private static final String C_P_73_3="Le bonheur de {0} est supérieur ou égal à {1} en montant de niveau.\n";
private static final String M_P_73_BASE="base";
private static final String M_P_73_HAPPY="happy";
private static final char SEP='=';
private MesDataPokemonEvolutionsEvohappiness(){}
static String en(){
String f=M_P_73_BASE+SEP+C_P_73_0;
f+=M_P_73_HAPPY+SEP+C_P_73_1;
return f;
}
static String fr(){
String f=M_P_73_BASE+SEP+C_P_73_2;
f+=M_P_73_HAPPY+SEP+C_P_73_3;
return f;
}
}
