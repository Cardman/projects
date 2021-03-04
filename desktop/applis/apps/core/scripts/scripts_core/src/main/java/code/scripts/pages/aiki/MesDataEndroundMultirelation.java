package code.scripts.pages.aiki;
final class MesDataEndroundMultirelation{
private MesDataEndroundMultirelation(){}
static String en(){
String f="effect=This effect regards all possible relations between users and targets on the front of battle.<br/>\n";
f+="damage_status=Here is the rate of lost hp in function by the combinaisons of the status of the foe of the figther (the hidden combinaisons of status have not effect.):<br/>\n";
f+="damage_status_key=Owned status\n";
f+="damage_status_rate=Rate of lost life by the foe\n";
return f;
}
static String fr(){
String f="effect=Cet effect prend en compte toutes les relations possibles entre lanceurs et cibles sur le terrains.<br/>\n";
f+="damage_status=Voici le taux de pv perdus en fonction des combinaisons des statuts de l''advsersaire du combattant (les combinaisons de statut non affich&eacute;es n''on aucun effet.):<br/>\n";
f+="damage_status_key=Statut poss&eacute;d&eacute;\n";
f+="damage_status_rate=Taux de vie perdue par l''advesaire\n";
return f;
}
}
