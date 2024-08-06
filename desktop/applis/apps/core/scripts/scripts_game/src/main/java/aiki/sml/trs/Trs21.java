package aiki.sml.trs;
import aiki.db.*;
public final class Trs21{
private Trs21(){}
static String tr(){
String f=DataBase.DEF_ENV_GRASS+"\tHERBE\n";
f+=DataBase.DEF_ENV_ROCK+"\tROCHE\n";
f+=DataBase.DEF_ENV_ROAD+"\tROUTE\n";
f+=DataBase.DEF_ENV_WATER+"\tEAU\n";
f+=DataBase.DEF_ENV_NOTHING+"\tRIEN\n";
f+=DataBase.DEF_ENV_SNOW+"\tNEIGE\n";
f+=DataBase.DEF_ENV_ICE+"\tGLACE\n";
f+=DataBase.DEF_ENV_DESERT+"\tDESERT\n";
f+=DataBase.DEF_ENV_BUILDING+"\tBATIMENT\n";
return f;
}
}
