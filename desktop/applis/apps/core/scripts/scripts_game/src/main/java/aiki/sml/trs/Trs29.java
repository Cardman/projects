package aiki.sml.trs;
public final class Trs29{
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
private Trs29(){}
static String tr(){
String f=TR_ACCURACY+"\tPRECISION\n";
f+=TR_PV_RESTANTS+"\tPV_RESTANTS\n";
f+=TR_EVASINESS+"\tESQUIVE\n";
f+=TR_ATTACK+"\tATTAQUE\n";
f+=TR_SPECIAL_ATTACK+"\tATTAQUE_SPECIALE\n";
f+=TR_SPECIAL_DEFENSE+"\tDEFENSE_SPECIALE\n";
f+=TR_HP+"\tPV\n";
f+=TR_CRITICAL_HIT+"\tCOUP_CRITIQUE\n";
f+=TR_DEFENSE+"\tDEFENSE\n";
f+=TR_SPEED+"\tVITESSE\n";
return f;
}
}
