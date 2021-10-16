package code.scripts.pages.aiki;
final class MesDataItemsHealinghpstatus{
private static final String C_P_23_0="The restored life rate of the pokemon is {0}.<br/>\n";
private static final String C_P_23_1="Le taux de vie restaur&eacute;e du pok&eacute;mon est de {0}.<br/>\n";
private static final String M_P_RATE="rate";
private static final char SEP='=';
private MesDataItemsHealinghpstatus(){}
static String en(){
String f=M_P_RATE+SEP+C_P_23_0;
return f;
}
static String fr(){
String f=M_P_RATE+SEP+C_P_23_1;
return f;
}
}
