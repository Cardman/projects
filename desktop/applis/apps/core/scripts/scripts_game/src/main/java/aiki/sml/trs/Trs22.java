package aiki.sml.trs;
public final class Trs22{
private static final String TR_NO_GENDER="NO_GENDER";
private static final String TR_FEMALE="FEMALE";
private static final String TR_MALE="MALE";
private Trs22(){}
static String tr(){
String f=TR_NO_GENDER+"\tASSEXUE\n";
f+=TR_FEMALE+"\tFEMELLE\n";
f+=TR_MALE+"\tMALE\n";
return f;
}
}
