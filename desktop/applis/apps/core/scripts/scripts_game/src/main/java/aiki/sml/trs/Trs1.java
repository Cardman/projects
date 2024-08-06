package aiki.sml.trs;
import aiki.db.*;
public final class Trs1{
private Trs1(){}
static String tr(){
String e=DataBase.DEF_SEL_BOOL_YES+"\tYES\n";
e+=DataBase.DEF_SEL_BOOL_NO+"\tNO\n";
e+=DataBase.DEF_SEL_BOOL_YES_AND_NO+"\t \n";
return e;
}
}
