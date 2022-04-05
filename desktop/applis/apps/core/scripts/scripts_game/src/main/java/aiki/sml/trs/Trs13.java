package aiki.sml.trs;
import aiki.sml.init.CstIgame;
public final class Trs13 extends CstIgame {
private Trs13(){}
static String tr(){
String e=I_POISON_GRAVE+"\tBAD_POISON\n";
e+=I_PEUR+"\tFLINCH\n";
e+=I_BRULURE+"\tBURN\n";
e+=I_VAMPIGRAINE_ST+"\tLEECH_SEED\n";
e+=I_GEL+"\tFREEZE\n";
e+=I_SIMPLE_POISON+"\tPOISON\n";
e+=I_AMOUR+"\tLOVE\n";
e+=I_SOMMEIL+"\tSLEEP\n";
e+=I_SOMMEIL_REPOS+"\tREST_SLEEP\n";
e+=I_MAUDIT+"\tCURSE\n";
e+=I_CAUCHEMAR_ST+"\tNIGHTMARE\n";
e+=I_CONFUSION+"\tCONFUSING\n";
e+=I_PARALYSIE+"\tPARALYSIS\n";
return e;
}
}
