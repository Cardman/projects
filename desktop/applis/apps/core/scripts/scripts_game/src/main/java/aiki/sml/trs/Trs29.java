package aiki.sml.trs;

import aiki.db.*;

public final class Trs29{
private Trs29(){}
static String tr(){
String f=DataBase.DEF_STAT_ACCURACY+"\tPRECISION\n";
f+=DataBase.DEF_STAT_PV_RESTANTS+"\tPV_RESTANTS\n";
f+=DataBase.DEF_STAT_EVASINESS+"\tESQUIVE\n";
f+=DataBase.DEF_STAT_ATTACK+"\tATTAQUE\n";
f+=DataBase.DEF_STAT_SPECIAL_ATTACK+"\tATTAQUE_SPECIALE\n";
f+=DataBase.DEF_STAT_SPECIAL_DEFENSE+"\tDEFENSE_SPECIALE\n";
f+=DataBase.DEF_STAT_HP+"\tPV\n";
f+=DataBase.DEF_STAT_CRITICAL_HIT+"\tCOUP_CRITIQUE\n";
f+=DataBase.DEF_STAT_DEFENSE+"\tDEFENSE\n";
f+=DataBase.DEF_STAT_SPEED+"\tVITESSE\n";
return f;
}
}
