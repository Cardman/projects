package aiki.sml.trs;
import aiki.db.*;
public final class Trs14{
private Trs14(){}
static String tr(){
String e=DataBase.DEF_TARGET_ADJ_ADV+"\tThe closest foes\n";
e+=DataBase.DEF_TARGET_ADJ_MULT+"\tAll closest fighters\n";
e+=DataBase.DEF_TARGET_ADJ_UNIQ+"\tA close fighter\n";
e+=DataBase.DEF_TARGET_ALLIE+"\tAn ally\n";
e+=DataBase.DEF_TARGET_ALLIES+"\tAllies\n";
e+=DataBase.DEF_TARGET_ANY_FOE+"\tA foe\n";
e+=DataBase.DEF_TARGET_AUTRE_UNIQ+"\tA fighter other than the user\n";
e+=DataBase.DEF_TARGET_GLOBALE+"\tAll fighters\n";
e+=DataBase.DEF_TARGET_LANCEUR+"\tThe user\n";
e+=DataBase.DEF_TARGET_PSEUDO_GLOBALE+"\tAll fighters but the user\n";
e+=DataBase.DEF_TARGET_TOUS_ADV+"\tAll foes\n";
e+=DataBase.DEF_TARGET_UNIQUE_IMPORTE+"\tA fighter\n";
e+=DataBase.DEF_TARGET_NOTHING+"\tNothing\n";
return e;
}
}
