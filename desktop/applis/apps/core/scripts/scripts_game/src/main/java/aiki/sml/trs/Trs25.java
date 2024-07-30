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
f+=MbOperationNode.ENT+"\tPartie enti&egrave;re\n";
//f+=MbOperationNode.TAUX+"\tNombre rationnel\n";
f+=MbOperationNode.TRONC+"\tTroncature de\n";
f+=MbOperationNode.NUM+"\tNum&eacute;rateur\n";
f+=MbOperationNode.DEN+"\tD&eacute;nominateur\n";
f+=MbOperationNode.MIN+"\tMinimum\n";
f+=MbOperationNode.MAX+"\tMaximum\n";
f+=MbOperationNode.MOY+"\tMoyenne\n";
f+=MbOperationNode.VAR+"\tVariance\n";
f+=MbOperationNode.CARAC_FERME+"\tCaract&eacute;ristique du ferm&eacute;\n";
f+=MbOperationNode.CARAC_OUVERT+"\tCaract&eacute;ristique de l'ouvert\n";
f+=MbOperationNode.CARAC_SEMI_OUVERT_G+"\tCaract&eacute;ristique de l'intervalle semi ouvert &agrave; gauche\n";
f+=MbOperationNode.CARAC_SEMI_OUVERT_D+"\tCaract&eacute;ristique de l'intervalle semi ouvert &agrave; droite\n";
f+=MbOperationNode.CARAC_GAUCHE_OUVERT+"\tCaract&eacute;ristique de l'intervalle ouvert &agrave; gauche\n";
f+=MbOperationNode.CARAC_DROITE_OUVERT+"\tCaract&eacute;ristique de l'intervalle ouvert &agrave; droite\n";
f+=MbOperationNode.CARAC_GAUCHE_FERME+"\tCaract&eacute;ristique de l'intervalle &agrave; gauche ferm&eacute;\n";
f+=MbOperationNode.CARAC_DROITE_FERME+"\tCaract&eacute;ristique de l'intervalle &agrave; droite ferm&eacute;\n";
f+=MbOperationNode.SGN+"\tSigne\n";
f+=MbOperationNode.CARD+"\tNombre d'&eacute;l&eacute;ments\n";
f+=MbOperationNode.INTER+"\tIntersection\n";
f+=MbOperationNode.UNION+"\tUnion\n";
f+=MbOperationNode.COMPL+"\tCompl&eacute;mentaire de\n";
f+=MbOperationNode.INCL+"\tInclusion num&eacute;rique\n";
f+=MbOperationNode.NON_INCL+"\tNon inclusion num&eacute;rique\n";
f+=MbOperationNode.EQ_NUM+"\tEgalit&eacute; num&eacute;rique\n";
f+=MbOperationNode.NON_EQ_NUM+"\tDiff&eacute;rence num&eacute;rique\n";
//f+=MbOperationNode.VIDE+"\tEnsemble vide\n";
return f;
}
}
