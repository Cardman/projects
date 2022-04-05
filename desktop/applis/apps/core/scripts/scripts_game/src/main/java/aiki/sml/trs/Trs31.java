package aiki.sml.trs;
public final class Trs31{
private static final String TR_ADJ_ADV="ADJ_ADV";
private static final String TR_ADJ_MULT="ADJ_MULT";
private static final String TR_ADJ_UNIQ="ADJ_UNIQ";
private static final String TR_ALLIE="ALLIE";
private static final String TR_ALLIES="ALLIES";
private static final String TR_ANY_FOE="ANY_FOE";
private static final String TR_AUTRE_UNIQ="AUTRE_UNIQ";
private static final String TR_GLOBALE="GLOBALE";
private static final String TR_LANCEUR="LANCEUR";
private static final String TR_PSEUDO_GLOBALE="PSEUDO_GLOBALE";
private static final String TR_TOUS_ADV="TOUS_ADV";
private static final String TR_UNIQUE_IMPORTE="UNIQUE_IMPORTE";
private static final String TR_NOTHING="NOTHING";
private Trs31(){}
static String tr(){
String f=TR_ADJ_ADV+"\tLes adversaires adjacents\n";
f+=TR_ADJ_MULT+"\tTous les combattants adjacents\n";
f+=TR_ADJ_UNIQ+"\tUn combattant adjacent\n";
f+=TR_ALLIE+"\tUn alli&eacute;\n";
f+=TR_ALLIES+"\tLes alli&eacute;s\n";
f+=TR_ANY_FOE+"\tUn adversaire\n";
f+=TR_AUTRE_UNIQ+"\tUn combattant autre que le lanceur\n";
f+=TR_GLOBALE+"\tTous les combattants\n";
f+=TR_LANCEUR+"\tLe lanceur\n";
f+=TR_PSEUDO_GLOBALE+"\tTous les combattants sauf le lanceur\n";
f+=TR_TOUS_ADV+"\tTous les adversaires\n";
f+=TR_UNIQUE_IMPORTE+"\tUn combatant\n";
f+=TR_NOTHING+"\tRien\n";
return f;
}
}
