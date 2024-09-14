package code.scripts.pages.aiki;
final class MesDataMapMap{
private static final String C_P_33_0="Return to the index\n";
private static final String C_P_33_1="Go to the level of the place\n";
private static final String C_P_33_2="Revenir à l''indexe\n";
private static final String C_P_33_3="Aller au niveau du lieu\n";
private static final String M_P_33_GOLEVEL="goLevel";
private static final String M_P_33_INDEX="index";
private static final char SEP='=';
private MesDataMapMap(){}
static String en(){
String f=M_P_33_INDEX+SEP+C_P_33_0;
f+=M_P_33_GOLEVEL+SEP+C_P_33_1;
return f;
}
static String fr(){
String f=M_P_33_INDEX+SEP+C_P_33_2;
f+=M_P_33_GOLEVEL+SEP+C_P_33_3;
return f;
}
}
