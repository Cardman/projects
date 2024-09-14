package code.scripts.pages.aiki;
final class MesDataEndroundMultirelation{
private static final String C_P_7_0="This effect regards all possible relations between users and targets on the front of battle.\n";
private static final String C_P_7_1="Here is the rate of lost hp in function by the combinaisons of the status of the foe of the figther (the hidden combinaisons of status have not effect.):\n";
private static final String C_P_7_2="Owned status\n";
private static final String C_P_7_3="Rate of lost life by the foe\n";
private static final String C_P_7_4="Cet effect prend en compte toutes les relations possibles entre lanceurs et cibles sur le terrains.\n";
private static final String C_P_7_5="Voici le taux de pv perdus en fonction des combinaisons des statuts de l''advsersaire du combattant (les combinaisons de statut non affichées n''on aucun effet.):\n";
private static final String C_P_7_6="Statut possédé\n";
private static final String C_P_7_7="Taux de vie perdue par l''advesaire\n";
private static final String M_P_7_DAMAGE_STATUS="damage_status";
private static final String M_P_7_DAMAGE_STATUS_KEY="damage_status_key";
private static final String M_P_7_DAMAGE_STATUS_RATE="damage_status_rate";
private static final String M_P_7_EFFECT="effect";
private static final char SEP='=';
private MesDataEndroundMultirelation(){}
static String en(){
String f=M_P_7_EFFECT+SEP+C_P_7_0;
f+=M_P_7_DAMAGE_STATUS+SEP+C_P_7_1;
f+=M_P_7_DAMAGE_STATUS_KEY+SEP+C_P_7_2;
f+=M_P_7_DAMAGE_STATUS_RATE+SEP+C_P_7_3;
return f;
}
static String fr(){
String f=M_P_7_EFFECT+SEP+C_P_7_4;
f+=M_P_7_DAMAGE_STATUS+SEP+C_P_7_5;
f+=M_P_7_DAMAGE_STATUS_KEY+SEP+C_P_7_6;
f+=M_P_7_DAMAGE_STATUS_RATE+SEP+C_P_7_7;
return f;
}
}
