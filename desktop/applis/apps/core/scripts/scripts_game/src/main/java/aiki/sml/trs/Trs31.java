package aiki.sml.trs;
public final class Trs31{
private Trs31(){}
static String tr(){
String f="ADJ_ADV\tLes adversaires adjacents\n";
f+="ADJ_MULT\tTous les combattants adjacents\n";
f+="ADJ_UNIQ\tUn combattant adjacent\n";
f+="ALLIE\tUn alli&eacute;\n";
f+="ALLIES\tLes alli&eacute;s\n";
f+="ANY_FOE\tUn adversaire\n";
f+="AUTRE_UNIQ\tUn combattant autre que le lanceur\n";
f+="GLOBALE\tTous les combattants\n";
f+="LANCEUR\tLe lanceur\n";
f+="PSEUDO_GLOBALE\tTous les combattants sauf le lanceur\n";
f+="TOUS_ADV\tTous les adversaires\n";
f+="UNIQUE_IMPORTE\tUn combatant\n";
f+="NOTHING\tRien\n";
return f;
}
}
