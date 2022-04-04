package code.scripts.pages.aiki;
final class MesDataItemsBall{
private static final String C_P_16_0="{0}\n";
private static final String C_P_16_1="The formula of the rate catching is the following one:\n";
private static final String C_P_16_2="{0}\n";
private static final String C_P_16_3="La formule du taux de capture est la suivante:\n";
private static final String M_P_16_FORMULA="formula";
private static final String M_P_16_RATE_CATCHING="rate_catching";
private static final char SEP='=';
private MesDataItemsBall(){}
static String en(){
String f=M_P_16_FORMULA+SEP+C_P_16_0;
f+=M_P_16_RATE_CATCHING+SEP+C_P_16_1;
return f;
}
static String fr(){
String f=M_P_16_FORMULA+SEP+C_P_16_2;
f+=M_P_16_RATE_CATCHING+SEP+C_P_16_3;
return f;
}
}
