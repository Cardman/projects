package aiki.sml.trs;
public final class Trs1{
private static final String TR_YES="YES";
private static final String TR_NO="NO";
private static final String TR_YES_AND_NO="YES_AND_NO";
private Trs1(){}
static String tr(){
String e=TR_YES+"\tYES\n";
e+=TR_NO+"\tNO\n";
e+=TR_YES_AND_NO+"\t \n";
return e;
}
}
