package code.scripts.pages.aiki;
final class MesDataItemsItem{
private static final String C_P_27_0="Data about the item {0}.\n";
private static final String C_P_27_1="The item {0} is worth {1}.\n";
private static final String C_P_27_2="{0}\n";
private static final String C_P_27_3="Items\n";
private static final String C_P_27_4="Donnés sur l''objet {0}.\n";
private static final String C_P_27_5="Le prix d''un objet {0} est de {1}.\n";
private static final String C_P_27_6="{0}\n";
private static final String C_P_27_7="Objets\n";
private static final String M_P_27_ITEMS="items";
private static final String M_P_27_ITEM_TYPE="item_type";
private static final String M_P_27_PRICE="price";
private static final String M_P_27_TITLE="title";
private static final char SEP='=';
private MesDataItemsItem(){}
static String en(){
String f=M_P_27_TITLE+SEP+C_P_27_0;
f+=M_P_27_PRICE+SEP+C_P_27_1;
f+=M_P_27_ITEM_TYPE+SEP+C_P_27_2;
f+=M_P_27_ITEMS+SEP+C_P_27_3;
return f;
}
static String fr(){
String f=M_P_27_TITLE+SEP+C_P_27_4;
f+=M_P_27_PRICE+SEP+C_P_27_5;
f+=M_P_27_ITEM_TYPE+SEP+C_P_27_6;
f+=M_P_27_ITEMS+SEP+C_P_27_7;
return f;
}
}
