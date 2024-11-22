package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffswitchitems{
private static final String C_P_61_0="This effect changes the owner of an item at least.\n";
private static final String C_P_61_10="La baie du combattant est supprimé définitivement.\n";
private static final String C_P_61_11="Le lanceur et la cible s''échangent les objets.\n";
private static final String C_P_61_12="La cible perd son objet. Le lanceur ne prend pas l''objet perdu par la cible.\n";
private static final String C_P_61_13="Le lanceur prend l''objet de la cible.\n";
private static final String C_P_61_14="Si possible, le lanceur utilise l''objet de la cible.\n";
//private static final String C_P_61_15="Si possible, le lanceur utilise l''objet de la cible.\n";
private static final String C_P_61_16="Le lanceur donne son objet à la cible.\n";
private static final String C_P_61_17="Le lanceur récupère le dernier objet qu''il a utilisé.\n";
private static final String C_P_61_1="The berry of the fighter is definitively deleted.\n";
private static final String C_P_61_2="The user and the target switch one each other the items.\n";
private static final String C_P_61_3="The target looses its item. The user does not take the lost item of the target.\n";
private static final String C_P_61_4="The user takes the item of the target.\n";
private static final String C_P_61_5="If possible, the user uses the item of the target.\n";
//private static final String C_P_61_6="If possible, the user uses the item of the target.\n";
private static final String C_P_61_7="The user gives its item to the target.\n";
private static final String C_P_61_8="The user gets the last item that the user used.\n";
private static final String C_P_61_9="Cet effet change le possesseur d''au moins un objet.\n";
private static final String M_P_61_DELETE_BERRY="delete_berry";
private static final String M_P_61_EFFECT="effect";
private static final String M_P_61_GIVE_TO_TARGET="give_to_target";
private static final String M_P_61_REMOVE_ITEM="remove_item";
private static final String M_P_61_REUSE_ITEM="reuse_item";
private static final String M_P_61_SWITCH_ITEMS="switch_items";
private static final String M_P_61_TAKE_ITEM="take_item";
//private static final String M_P_61_USE_AS_POSSIBLE="use_as_possible";
private static final String M_P_61_USE_ITEM_IF_POSSIBLE="use_item_if_possible";
private static final char SEP='=';
private MesDataMovesEffectsEffswitchitems(){}
static String en(){
String f=M_P_61_EFFECT+SEP+C_P_61_0;
f+=M_P_61_DELETE_BERRY+SEP+C_P_61_1;
f+=M_P_61_SWITCH_ITEMS+SEP+C_P_61_2;
f+=M_P_61_REMOVE_ITEM+SEP+C_P_61_3;
f+=M_P_61_TAKE_ITEM+SEP+C_P_61_4;
f+=M_P_61_USE_ITEM_IF_POSSIBLE+SEP+C_P_61_5;
//f+=M_P_61_USE_AS_POSSIBLE+SEP+C_P_61_6;
f+=M_P_61_GIVE_TO_TARGET+SEP+C_P_61_7;
f+=M_P_61_REUSE_ITEM+SEP+C_P_61_8;
return f;
}
static String fr(){
String f=M_P_61_EFFECT+SEP+C_P_61_9;
f+=M_P_61_DELETE_BERRY+SEP+C_P_61_10;
f+=M_P_61_SWITCH_ITEMS+SEP+C_P_61_11;
f+=M_P_61_REMOVE_ITEM+SEP+C_P_61_12;
f+=M_P_61_TAKE_ITEM+SEP+C_P_61_13;
f+=M_P_61_USE_ITEM_IF_POSSIBLE+SEP+C_P_61_14;
//f+=M_P_61_USE_AS_POSSIBLE+SEP+C_P_61_15;
f+=M_P_61_GIVE_TO_TARGET+SEP+C_P_61_16;
f+=M_P_61_REUSE_ITEM+SEP+C_P_61_17;
return f;
}
}
