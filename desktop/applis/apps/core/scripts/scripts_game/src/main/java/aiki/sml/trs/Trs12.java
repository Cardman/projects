package aiki.sml.trs;
public final class Trs12{
private static final String E_TR_ACCURACY="ACCURACY";
private static final String E_TR_PV_RESTANTS="PV_RESTANTS";
private static final String E_TR_EVASINESS="EVASINESS";
private static final String E_TR_ATTACK="ATTACK";
private static final String E_TR_SPECIAL_ATTACK="SPECIAL_ATTACK";
private static final String E_TR_SPECIAL_DEFENSE="SPECIAL_DEFENSE";
private static final String E_TR_HP="HP";
private static final String E_TR_CRITICAL_HIT="CRITICAL_HIT";
private static final String E_TR_DEFENSE="DEFENSE";
private static final String E_TR_SPEED="SPEED";
private Trs12(){}
static String tr(){
String e=E_TR_ACCURACY+"\tACCURACY\n";
e+=E_TR_PV_RESTANTS+"\tLEFT_HP\n";
e+=E_TR_EVASINESS+"\tEVASINESS\n";
e+=E_TR_ATTACK+"\tATTACK\n";
e+=E_TR_SPECIAL_ATTACK+"\tSPECIAL_ATTACK\n";
e+=E_TR_SPECIAL_DEFENSE+"\tSPECIAL_DEFENSE\n";
e+=E_TR_HP+"\tHP\n";
e+=E_TR_CRITICAL_HIT+"\tCRITICAL_HIT\n";
e+=E_TR_DEFENSE+"\tDEFENSE\n";
e+=E_TR_SPEED+"\tSPEED\n";
return e;
}
}
