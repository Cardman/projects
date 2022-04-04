package code.scripts.pages.aiki;
final class MesDataItemsHealingpp{
private static final String C_P_25_0="This item can restore {0} pp of one attack.\n";
private static final String C_P_25_1="This item can restore {0} pp of all attacks.\n";
private static final String C_P_25_2="This item can restore all pp of one attack.\n";
private static final String C_P_25_3="This item can restore all pp of all attacks.\n";
private static final String C_P_25_4="Cet objet permet de soigner {0} pp d''une attaque.\n";
private static final String C_P_25_5="Cet objet permet de soigner {0} pp de toutes les attaques.\n";
private static final String C_P_25_6="Cet objet permet de soigner tous les pp d''une attaque.\n";
private static final String C_P_25_7="Cet objet permet de soigner tous les pp de toutes les attaques.\n";
private static final String M_P_25_FULL_HEAL_MOVE="full_heal_move";
private static final String M_P_25_FULL_HEAL_MOVES="full_heal_moves";
private static final String M_P_25_HEAL_MOVE="heal_move";
private static final String M_P_25_HEAL_MOVES="heal_moves";
private static final char SEP='=';
private MesDataItemsHealingpp(){}
static String en(){
String f=M_P_25_HEAL_MOVE+SEP+C_P_25_0;
f+=M_P_25_HEAL_MOVES+SEP+C_P_25_1;
f+=M_P_25_FULL_HEAL_MOVE+SEP+C_P_25_2;
f+=M_P_25_FULL_HEAL_MOVES+SEP+C_P_25_3;
return f;
}
static String fr(){
String f=M_P_25_HEAL_MOVE+SEP+C_P_25_4;
f+=M_P_25_HEAL_MOVES+SEP+C_P_25_5;
f+=M_P_25_FULL_HEAL_MOVE+SEP+C_P_25_6;
f+=M_P_25_FULL_HEAL_MOVES+SEP+C_P_25_7;
return f;
}
}
