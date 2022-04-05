package aiki.sml.trs;
public final class Trs29{
private static final String F_TR_ACCURACY="ACCURACY";
private static final String F_TR_PV_RESTANTS="PV_RESTANTS";
private static final String F_TR_EVASINESS="EVASINESS";
private static final String F_TR_ATTACK="ATTACK";
private static final String F_TR_SPECIAL_ATTACK="SPECIAL_ATTACK";
private static final String F_TR_SPECIAL_DEFENSE="SPECIAL_DEFENSE";
private static final String F_TR_HP="HP";
private static final String F_TR_CRITICAL_HIT="CRITICAL_HIT";
private static final String F_TR_DEFENSE="DEFENSE";
private static final String F_TR_SPEED="SPEED";
private Trs29(){}
static String tr(){
String f=F_TR_ACCURACY+"\tPRECISION\n";
f+=F_TR_PV_RESTANTS+"\tPV_RESTANTS\n";
f+=F_TR_EVASINESS+"\tESQUIVE\n";
f+=F_TR_ATTACK+"\tATTAQUE\n";
f+=F_TR_SPECIAL_ATTACK+"\tATTAQUE_SPECIALE\n";
f+=F_TR_SPECIAL_DEFENSE+"\tDEFENSE_SPECIALE\n";
f+=F_TR_HP+"\tPV\n";
f+=F_TR_CRITICAL_HIT+"\tCOUP_CRITIQUE\n";
f+=F_TR_DEFENSE+"\tDEFENSE\n";
f+=F_TR_SPEED+"\tVITESSE\n";
return f;
}
}
