package code.scripts.pages.aiki;
final class MesDataItemsRepel{
private static final String C_P_30_0="The wild pokemon are repelled during {0} steps.\n";
private static final String C_P_30_1="Les pokémons sauvages sont repoussés pendant {0} pas.\n";
private static final String M_P_30_STEPS="steps";
private static final char SEP='=';
private MesDataItemsRepel(){}
static String en(){
return M_P_30_STEPS+SEP+C_P_30_0;
}
static String fr(){
return M_P_30_STEPS+SEP+C_P_30_1;
}
}
