package aiki.sml.trs;
import aiki.sml.init.CstIgame;
public final class Trs2 extends CstIgame {
private Trs2(){}
static String tr(){
String e=I_AUTRE+"\tOTHER\n";
e+=I_SPECIALE+"\tSPECIAL\n";
e+=I_PHYSIQUE+"\tPHYSICAL\n";
return e;
}
}
