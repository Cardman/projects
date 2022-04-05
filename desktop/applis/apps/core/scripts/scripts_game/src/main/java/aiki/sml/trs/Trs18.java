package aiki.sml.trs;
public final class Trs18{
private static final String TR_YES="YES";
private static final String TR_NO="NO";
private static final String TR_YES_AND_NO="YES_AND_NO";
private Trs18(){}
static String tr(){
String f=TR_YES+"\tOUI\n";
f+=TR_NO+"\tNON\n";
f+=TR_YES_AND_NO+"\t \n";
return f;
}
}
