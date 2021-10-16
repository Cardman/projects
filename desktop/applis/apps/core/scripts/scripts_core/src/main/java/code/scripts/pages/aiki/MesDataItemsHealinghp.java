package code.scripts.pages.aiki;
final class MesDataItemsHealinghp{
private static final String C_P_22_0="This item can restores {0} hp.<br/>\n";
private static final String C_P_22_1="Cet objet permet de soigner {0} pv.<br/>\n";
private static final String M_P_HEAL_HP="heal_hp";
private static final char SEP='=';
private MesDataItemsHealinghp(){}
static String en(){
String f=M_P_HEAL_HP+SEP+C_P_22_0;
return f;
}
static String fr(){
String f=M_P_HEAL_HP+SEP+C_P_22_1;
return f;
}
}
