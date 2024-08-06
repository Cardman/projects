package aiki.sml.trs;
import aiki.db.*;
public final class Trs18{
private Trs18(){}
static String tr(){
String f=DataBase.DEF_SEL_BOOL_YES+"\tOUI\n";
f+=DataBase.DEF_SEL_BOOL_NO+"\tNON\n";
f+=DataBase.DEF_SEL_BOOL_YES_AND_NO+"\t \n";
return f;
}
}
