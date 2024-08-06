package aiki.sml.trs;

import aiki.db.*;

public final class Trs5{
private Trs5(){}
static String tr(){
String e=DataBase.DEF_GENDER_NO_GENDER+"\tNO_GENDER\n";
e+=DataBase.DEF_GENDER_FEMALE+"\tFEMALE\n";
e+=DataBase.DEF_GENDER_MALE+"\tMALE\n";
return e;
}
}
