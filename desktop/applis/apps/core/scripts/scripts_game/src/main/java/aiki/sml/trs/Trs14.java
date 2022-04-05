package aiki.sml.trs;
public final class Trs14{
private static final String E_TR_ADJ_ADV="ADJ_ADV";
private static final String E_TR_ADJ_MULT="ADJ_MULT";
private static final String E_TR_ADJ_UNIQ="ADJ_UNIQ";
private static final String E_TR_ALLIE="ALLIE";
private static final String E_TR_ALLIES="ALLIES";
private static final String E_TR_ANY_FOE="ANY_FOE";
private static final String E_TR_AUTRE_UNIQ="AUTRE_UNIQ";
private static final String E_TR_GLOBALE="GLOBALE";
private static final String E_TR_LANCEUR="LANCEUR";
private static final String E_TR_PSEUDO_GLOBALE="PSEUDO_GLOBALE";
private static final String E_TR_TOUS_ADV="TOUS_ADV";
private static final String E_TR_UNIQUE_IMPORTE="UNIQUE_IMPORTE";
private static final String E_TR_NOTHING="NOTHING";
private Trs14(){}
static String tr(){
String e=E_TR_ADJ_ADV+"\tThe closest foes\n";
e+=E_TR_ADJ_MULT+"\tAll closest fighters\n";
e+=E_TR_ADJ_UNIQ+"\tA close fighter\n";
e+=E_TR_ALLIE+"\tAn ally\n";
e+=E_TR_ALLIES+"\tAllies\n";
e+=E_TR_ANY_FOE+"\tA foe\n";
e+=E_TR_AUTRE_UNIQ+"\tA fighter other than the user\n";
e+=E_TR_GLOBALE+"\tAll fighters\n";
e+=E_TR_LANCEUR+"\tThe user\n";
e+=E_TR_PSEUDO_GLOBALE+"\tAll fighters but the user\n";
e+=E_TR_TOUS_ADV+"\tAll foes\n";
e+=E_TR_UNIQUE_IMPORTE+"\tA fighter\n";
e+=E_TR_NOTHING+"\tNothing\n";
return e;
}
}
