package aiki.sml.trs;
import aiki.db.DataBase;
public final class Trs4{
private Trs4(){}
static String tr(){
String e=DataBase.DEF_ENV_GRASS+"\tGRASS\n";
e+=DataBase.DEF_ENV_ROCK+"\tROCK\n";
e+=DataBase.DEF_ENV_ROAD+"\tROAD\n";
e+=DataBase.DEF_ENV_WATER+"\tWATER\n";
e+=DataBase.DEF_ENV_NOTHING+"\tNOTHING\n";
e+=DataBase.DEF_ENV_SNOW+"\tSNOW\n";
e+=DataBase.DEF_ENV_ICE+"\tICE\n";
e+=DataBase.DEF_ENV_DESERT+"\tDESERT\n";
e+=DataBase.DEF_ENV_BUILDING+"\tBUILDING\n";
return e;
}
}
