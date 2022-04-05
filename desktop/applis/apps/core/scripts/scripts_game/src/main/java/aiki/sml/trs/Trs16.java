package aiki.sml.trs;
public final class Trs16{
private Trs16(){}
static String tr(){
String f=Cst5.CS_TRES_DIFFICILE+"\tVery hard\n";
f+=Cst5.CS_DIFFICILE+"\tHard\n";
f+=Cst5.CS_TRES_FACILE+"\tVery easy\n";
f+=Cst5.CS_FACILE+"\tEasy\n";
return f;
}
}
