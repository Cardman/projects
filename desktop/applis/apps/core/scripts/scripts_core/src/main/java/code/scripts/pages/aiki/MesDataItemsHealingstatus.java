package code.scripts.pages.aiki;
final class MesDataItemsHealingstatus{
private static final String C_P_26_0="This item can resuscitate a KO pokemon.<br/>\n";
private static final String C_P_26_1="This item can removes the following status of one pokemon:<br/>\n";
private static final String C_P_26_2="Cet objet permet de r&eacute;animer un pok&eacute;mon KO.<br/>\n";
private static final String C_P_26_3="Cet objet permet d''enlever les statuts suivants d''un pok&eacute;mon:<br/>\n";
private static final String M_P_HEAL_KO="heal_ko";
private static final String M_P_STATUS="status";
private static final char SEP='=';
private MesDataItemsHealingstatus(){}
static String en(){
String f=M_P_HEAL_KO+SEP+C_P_26_0;
f+=M_P_STATUS+SEP+C_P_26_1;
return f;
}
static String fr(){
String f=M_P_HEAL_KO+SEP+C_P_26_2;
f+=M_P_STATUS+SEP+C_P_26_3;
return f;
}
}
