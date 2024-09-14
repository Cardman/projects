package code.scripts.pages.aiki;
final class MesDataItemsHealinghpstatus{
private static final String C_P_23_0="The restored life rate of the pokemon is {0}.\n";
private static final String C_P_23_1="Le taux de vie restaurée du pokémon est de {0}.\n";
private static final String M_P_23_RATE="rate";
private static final char SEP='=';
private MesDataItemsHealinghpstatus(){}
static String en(){
return M_P_23_RATE+SEP+C_P_23_0;
}
static String fr(){
return M_P_23_RATE+SEP+C_P_23_1;
}
}
