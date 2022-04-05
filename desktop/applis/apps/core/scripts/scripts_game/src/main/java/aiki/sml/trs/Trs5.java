package aiki.sml.trs;
public final class Trs5{
private static final String TR_NO_GENDER="NO_GENDER";
private static final String TR_FEMALE="FEMALE";
private static final String TR_MALE="MALE";
private Trs5(){}
static String tr(){
String e=TR_NO_GENDER+"\tNO_GENDER\n";
e+=TR_FEMALE+"\tFEMALE\n";
e+=TR_MALE+"\tMALE\n";
return e;
}
}
