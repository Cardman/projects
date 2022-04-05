package aiki.sml.trs;
public final class Trs12{
private static final String TR_ACCURACY="ACCURACY";
private static final String TR_PV_RESTANTS="PV_RESTANTS";
private static final String TR_EVASINESS="EVASINESS";
private static final String TR_ATTACK="ATTACK";
private static final String TR_SPECIAL_ATTACK="SPECIAL_ATTACK";
private static final String TR_SPECIAL_DEFENSE="SPECIAL_DEFENSE";
private static final String TR_HP="HP";
private static final String TR_CRITICAL_HIT="CRITICAL_HIT";
private static final String TR_DEFENSE="DEFENSE";
private static final String TR_SPEED="SPEED";
private Trs12(){}
static String tr(){
String e=TR_ACCURACY+"\tACCURACY\n";
e+=TR_PV_RESTANTS+"\tLEFT_HP\n";
e+=TR_EVASINESS+"\tEVASINESS\n";
e+=TR_ATTACK+"\tATTACK\n";
e+=TR_SPECIAL_ATTACK+"\tSPECIAL_ATTACK\n";
e+=TR_SPECIAL_DEFENSE+"\tSPECIAL_DEFENSE\n";
e+=TR_HP+"\tHP\n";
e+=TR_CRITICAL_HIT+"\tCRITICAL_HIT\n";
e+=TR_DEFENSE+"\tDEFENSE\n";
e+=TR_SPEED+"\tSPEED\n";
return e;
}
}
