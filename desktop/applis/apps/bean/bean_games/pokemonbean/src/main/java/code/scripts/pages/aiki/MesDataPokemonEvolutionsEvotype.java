package code.scripts.pages.aiki;
final class MesDataPokemonEvolutionsEvotype{
private static final String C_P_81_0="{0} evolves into {1}.\n";
private static final String C_P_81_1="{0} must know at least one move with the type {1}.\n";
private static final String C_P_81_2="{0} évolue en {1}.\n";
private static final String C_P_81_3="{0} doit connaître au moins une attaque de type {1}.\n";
private static final String M_P_81_BASE="base";
private static final String M_P_81_TYPE="type";
private static final char SEP='=';
private MesDataPokemonEvolutionsEvotype(){}
static String en(){
String f=M_P_81_BASE+SEP+C_P_81_0;
f+=M_P_81_TYPE+SEP+C_P_81_1;
return f;
}
static String fr(){
String f=M_P_81_BASE+SEP+C_P_81_2;
f+=M_P_81_TYPE+SEP+C_P_81_3;
return f;
}
}
