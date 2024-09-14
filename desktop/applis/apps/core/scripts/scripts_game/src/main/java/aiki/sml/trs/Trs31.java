package aiki.sml.trs;
import aiki.db.*;
public final class Trs31{
private Trs31(){}
static String tr(){
String f=DataBase.DEF_TARGET_ADJ_ADV+"\tLes adversaires adjacents\n";
f+=DataBase.DEF_TARGET_ADJ_MULT+"\tTous les combattants adjacents\n";
f+=DataBase.DEF_TARGET_ADJ_UNIQ+"\tUn combattant adjacent\n";
f+=DataBase.DEF_TARGET_ALLIE+"\tUn allié\n";
f+=DataBase.DEF_TARGET_ALLIES+"\tLes alliés\n";
f+=DataBase.DEF_TARGET_ANY_FOE+"\tUn adversaire\n";
f+=DataBase.DEF_TARGET_AUTRE_UNIQ+"\tUn combattant autre que le lanceur\n";
f+=DataBase.DEF_TARGET_GLOBALE+"\tTous les combattants\n";
f+=DataBase.DEF_TARGET_LANCEUR+"\tLe lanceur\n";
f+=DataBase.DEF_TARGET_PSEUDO_GLOBALE+"\tTous les combattants sauf le lanceur\n";
f+=DataBase.DEF_TARGET_TOUS_ADV+"\tTous les adversaires\n";
f+=DataBase.DEF_TARGET_UNIQUE_IMPORTE+"\tUn combatant\n";
f+=DataBase.DEF_TARGET_NOTHING+"\tRien\n";
return f;
}
}
