package aiki.sml.trs;

import aiki.db.*;

public final class Trs12{
private Trs12(){}
static String tr(){
String e=DataBase.DEF_STAT_ACCURACY+"\tACCURACY\n";
e+=DataBase.DEF_STAT_PV_RESTANTS+"\tLEFT_HP\n";
e+=DataBase.DEF_STAT_EVASINESS+"\tEVASINESS\n";
e+=DataBase.DEF_STAT_ATTACK+"\tATTACK\n";
e+=DataBase.DEF_STAT_SPECIAL_ATTACK+"\tSPECIAL_ATTACK\n";
e+=DataBase.DEF_STAT_SPECIAL_DEFENSE+"\tSPECIAL_DEFENSE\n";
e+=DataBase.DEF_STAT_HP+"\tHP\n";
e+=DataBase.DEF_STAT_CRITICAL_HIT+"\tCRITICAL_HIT\n";
e+=DataBase.DEF_STAT_DEFENSE+"\tDEFENSE\n";
e+=DataBase.DEF_STAT_SPEED+"\tSPEED\n";
return e;
}
}
