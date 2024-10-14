package aiki.sml.trs;
import code.maths.litteral.*;
import aiki.sml.init.*;
import code.sml.util.*;
public final class MessagesTrsMath extends CstIgame{
private MessagesTrsMath(){}
static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(33);
e.add(MbOperationNode.DIV_FCT,"Division");
e.add(MbOperationNode.PUIS,"Power");
e.add(MbOperationNode.QUOT,"Rate of the integer division");
e.add(MbOperationNode.MOD,"Remaining number of the integer division");
e.add(MbOperationNode.MODTAUX,"Remaining number of the division");
e.add(MbOperationNode.ABS,"Absolute value");
e.add(MbOperationNode.ENT,"Integer part");
//e.add(MbOperationNode.TAUX,"Rational number");
e.add(MbOperationNode.TRONC,"Troncating");
e.add(MbOperationNode.NUM,"Numerator");
e.add(MbOperationNode.DEN,"Divider");
e.add(MbOperationNode.MIN,"Minimum");
e.add(MbOperationNode.MAX,"Maximum");
e.add(MbOperationNode.MOY,"Average");
e.add(MbOperationNode.VAR,"Variance");
e.add(MbOperationNode.CARAC_FERME,"Characteristic of the closed");
e.add(MbOperationNode.CARAC_OUVERT,"Characteristic of the opened");
e.add(MbOperationNode.CARAC_SEMI_OUVERT_G,"Characteristic of the left half opened interval");
e.add(MbOperationNode.CARAC_SEMI_OUVERT_D,"Characteristic of the right half opened interval");
e.add(MbOperationNode.CARAC_GAUCHE_OUVERT,"Characteristic of the left opened interval");
e.add(MbOperationNode.CARAC_DROITE_OUVERT,"Characteristic of the right opened interval");
e.add(MbOperationNode.CARAC_GAUCHE_FERME,"Characteristic of the closed left interval");
e.add(MbOperationNode.CARAC_DROITE_FERME,"Characteristic of the closed right interval");
e.add(MbOperationNode.SGN,"Signum");
e.add(MbOperationNode.CARD,"Elements count");
e.add(MbOperationNode.INTER,"Intersect");
e.add(MbOperationNode.UNION,"Union");
e.add(MbOperationNode.COMPL,"Complement of");
e.add(MbOperationNode.INCL,"Numeric include");
e.add(MbOperationNode.NON_INCL,"Non numeric include");
e.add(MbOperationNode.EQ_NUM,"Numeric equality");
e.add(MbOperationNode.NON_EQ_NUM,"Numeric difference");
//e.add(MbOperationNode.VIDE,"Empty set");
return e;
}
static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(33);
f.add(MbOperationNode.DIV_FCT,"Division");
f.add(MbOperationNode.PUIS,"Puissance");
f.add(MbOperationNode.QUOT,"Quotient de la division euclidienne");
f.add(MbOperationNode.MOD,"Reste de la division euclidienne");
f.add(MbOperationNode.MODTAUX,"Reste de la division");
f.add(MbOperationNode.ABS,"Valeur absolue");
f.add(MbOperationNode.ENT,"Partie entière");
//f.add(MbOperationNode.TAUX,"Nombre rationnel");
f.add(MbOperationNode.TRONC,"Troncature de");
f.add(MbOperationNode.NUM,"Numérateur");
f.add(MbOperationNode.DEN,"Dénominateur");
f.add(MbOperationNode.MIN,"Minimum");
f.add(MbOperationNode.MAX,"Maximum");
f.add(MbOperationNode.MOY,"Moyenne");
f.add(MbOperationNode.VAR,"Variance");
f.add(MbOperationNode.CARAC_FERME,"Caractéristique du fermé");
f.add(MbOperationNode.CARAC_OUVERT,"Caractéristique de l'ouvert");
f.add(MbOperationNode.CARAC_SEMI_OUVERT_G,"Caractéristique de l'intervalle semi ouvert à gauche");
f.add(MbOperationNode.CARAC_SEMI_OUVERT_D,"Caractéristique de l'intervalle semi ouvert à droite");
f.add(MbOperationNode.CARAC_GAUCHE_OUVERT,"Caractéristique de l'intervalle ouvert à gauche");
f.add(MbOperationNode.CARAC_DROITE_OUVERT,"Caractéristique de l'intervalle ouvert à droite");
f.add(MbOperationNode.CARAC_GAUCHE_FERME,"Caractéristique de l'intervalle à gauche fermé");
f.add(MbOperationNode.CARAC_DROITE_FERME,"Caractéristique de l'intervalle à droite fermé");
f.add(MbOperationNode.SGN,"Signe");
f.add(MbOperationNode.CARD,"Nombre d'éléments");
f.add(MbOperationNode.INTER,"Intersection");
f.add(MbOperationNode.UNION,"Union");
f.add(MbOperationNode.COMPL,"Complémentaire de");
f.add(MbOperationNode.INCL,"Inclusion numérique");
f.add(MbOperationNode.NON_INCL,"Non inclusion numérique");
f.add(MbOperationNode.EQ_NUM,"Égalité numérique");
f.add(MbOperationNode.NON_EQ_NUM,"Différence numérique");
//f.add(MbOperationNode.VIDE,"Ensemble vide");
return f;
}
}
