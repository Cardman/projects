package aiki.sml.trs;
import aiki.db.*;
import aiki.sml.init.*;
import code.sml.util.*;
public final class MessagesTrsTargets extends CstIgame{
private MessagesTrsTargets(){}
static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(13);
e.add(DataBase.DEF_TARGET_ADJ_ADV,"The closest foes");
e.add(DataBase.DEF_TARGET_ADJ_MULT,"All closest fighters");
e.add(DataBase.DEF_TARGET_ADJ_UNIQ,"A close fighter");
e.add(DataBase.DEF_TARGET_ALLIE,"An ally");
e.add(DataBase.DEF_TARGET_ALLIES,"Allies");
e.add(DataBase.DEF_TARGET_ANY_FOE,"A foe");
e.add(DataBase.DEF_TARGET_AUTRE_UNIQ,"A fighter other than the user");
e.add(DataBase.DEF_TARGET_GLOBALE,"All fighters");
e.add(DataBase.DEF_TARGET_LANCEUR,"The user");
e.add(DataBase.DEF_TARGET_PSEUDO_GLOBALE,"All fighters but the user");
e.add(DataBase.DEF_TARGET_TOUS_ADV,"All foes");
e.add(DataBase.DEF_TARGET_UNIQUE_IMPORTE,"A fighter");
e.add(DataBase.DEF_TARGET_NOTHING,"Nothing");
return e;
}
static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(13);
f.add(DataBase.DEF_TARGET_ADJ_ADV,"Les adversaires adjacents");
f.add(DataBase.DEF_TARGET_ADJ_MULT,"Tous les combattants adjacents");
f.add(DataBase.DEF_TARGET_ADJ_UNIQ,"Un combattant adjacent");
f.add(DataBase.DEF_TARGET_ALLIE,"Un allié");
f.add(DataBase.DEF_TARGET_ALLIES,"Les alliés");
f.add(DataBase.DEF_TARGET_ANY_FOE,"Un adversaire");
f.add(DataBase.DEF_TARGET_AUTRE_UNIQ,"Un combattant autre que le lanceur");
f.add(DataBase.DEF_TARGET_GLOBALE,"Tous les combattants");
f.add(DataBase.DEF_TARGET_LANCEUR,"Le lanceur");
f.add(DataBase.DEF_TARGET_PSEUDO_GLOBALE,"Tous les combattants sauf le lanceur");
f.add(DataBase.DEF_TARGET_TOUS_ADV,"Tous les adversaires");
f.add(DataBase.DEF_TARGET_UNIQUE_IMPORTE,"Un combatant");
f.add(DataBase.DEF_TARGET_NOTHING,"Rien");
return f;
}
}
