package aiki.sml.trs;
public final class Trs14{
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
private Trs14(){}
static String tr(){
String e=TR_ADJ_ADV+"\tThe closest foes\n";
e+=TR_ADJ_MULT+"\tAll closest fighters\n";
e+=TR_ADJ_UNIQ+"\tA close fighter\n";
e+=TR_ALLIE+"\tAn ally\n";
e+=TR_ALLIES+"\tAllies\n";
e+=TR_ANY_FOE+"\tA foe\n";
e+=TR_AUTRE_UNIQ+"\tA fighter other than the user\n";
e+=TR_GLOBALE+"\tAll fighters\n";
e+=TR_LANCEUR+"\tThe user\n";
e+=TR_PSEUDO_GLOBALE+"\tAll fighters but the user\n";
e+=TR_TOUS_ADV+"\tAll foes\n";
e+=TR_UNIQUE_IMPORTE+"\tA fighter\n";
e+=TR_NOTHING+"\tNothing\n";
return e;
}
}
