package aiki.sml.trs;
import code.maths.litteral.*;
public final class Trs25{
private Trs25(){}
static String tr(){
String f=MbOperationNode.DIV_FCT+"\tDivision\n";
f+=MbOperationNode.PUIS+"\tPuissance\n";
f+=MbOperationNode.QUOT+"\tQuotient de la division euclidienne\n";
f+=MbOperationNode.MOD+"\tReste de la division euclidienne\n";
f+=MbOperationNode.MODTAUX+"\tReste de la division\n";
f+=MbOperationNode.ABS+"\tValeur absolue\n";
f+=MbOperationNode.ENT+"\tPartie entière\n";
//f+=MbOperationNode.TAUX+"\tNombre rationnel\n";
f+=MbOperationNode.TRONC+"\tTroncature de\n";
f+=MbOperationNode.NUM+"\tNumérateur\n";
f+=MbOperationNode.DEN+"\tDénominateur\n";
f+=MbOperationNode.MIN+"\tMinimum\n";
f+=MbOperationNode.MAX+"\tMaximum\n";
f+=MbOperationNode.MOY+"\tMoyenne\n";
f+=MbOperationNode.VAR+"\tVariance\n";
f+=MbOperationNode.CARAC_FERME+"\tCaractéristique du fermé\n";
f+=MbOperationNode.CARAC_OUVERT+"\tCaractéristique de l'ouvert\n";
f+=MbOperationNode.CARAC_SEMI_OUVERT_G+"\tCaractéristique de l'intervalle semi ouvert à gauche\n";
f+=MbOperationNode.CARAC_SEMI_OUVERT_D+"\tCaractéristique de l'intervalle semi ouvert à droite\n";
f+=MbOperationNode.CARAC_GAUCHE_OUVERT+"\tCaractéristique de l'intervalle ouvert à gauche\n";
f+=MbOperationNode.CARAC_DROITE_OUVERT+"\tCaractéristique de l'intervalle ouvert à droite\n";
f+=MbOperationNode.CARAC_GAUCHE_FERME+"\tCaractéristique de l'intervalle à gauche fermé\n";
f+=MbOperationNode.CARAC_DROITE_FERME+"\tCaractéristique de l'intervalle à droite fermé\n";
f+=MbOperationNode.SGN+"\tSigne\n";
f+=MbOperationNode.CARD+"\tNombre d'éléments\n";
f+=MbOperationNode.INTER+"\tIntersection\n";
f+=MbOperationNode.UNION+"\tUnion\n";
f+=MbOperationNode.COMPL+"\tComplémentaire de\n";
f+=MbOperationNode.INCL+"\tInclusion numérique\n";
f+=MbOperationNode.NON_INCL+"\tNon inclusion numérique\n";
f+=MbOperationNode.EQ_NUM+"\tÉgalité numérique\n";
f+=MbOperationNode.NON_EQ_NUM+"\tDifférence numérique\n";
//f+=MbOperationNode.VIDE+"\tEnsemble vide\n";
return f;
}
}
