package aiki.sml.trs;

import aiki.db.*;

public final class Trs22{
private Trs22(){}
static String tr(){
String f= DataBase.DEF_GENDER_NO_GENDER+"\tASSEXUE\n";
f+=DataBase.DEF_GENDER_FEMALE+"\tFEMELLE\n";
f+=DataBase.DEF_GENDER_MALE+"\tMALE\n";
return f;
}
}
