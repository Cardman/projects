package aiki.sml.trs;
import code.maths.litteral.*;
public final class Trs8{
private Trs8(){}
static String tr(){
String e=MbOperationNode.DIV_FCT+"\tDivision\n";
e+=MbOperationNode.PUIS+"\tPower\n";
e+=MbOperationNode.QUOT+"\tRate of the integer division\n";
e+=MbOperationNode.MOD+"\tRemaining number of the integer division\n";
e+=MbOperationNode.MODTAUX+"\tRemaining number of the division\n";
e+=MbOperationNode.ABS+"\tAbsolute value\n";
e+=MbOperationNode.ENT+"\tInteger part\n";
//e+=MbOperationNode.TAUX+"\tRational number\n";
e+=MbOperationNode.TRONC+"\tTroncating\n";
e+=MbOperationNode.NUM+"\tNumerator\n";
e+=MbOperationNode.DEN+"\tDivider\n";
e+=MbOperationNode.MIN+"\tMinimum\n";
e+=MbOperationNode.MAX+"\tMaximum\n";
e+=MbOperationNode.MOY+"\tAverage\n";
e+=MbOperationNode.VAR+"\tVariance\n";
e+=MbOperationNode.CARAC_FERME+"\tCharacteristic of the closed\n";
e+=MbOperationNode.CARAC_OUVERT+"\tCharacteristic of the opened\n";
e+=MbOperationNode.CARAC_SEMI_OUVERT_G+"\tCharacteristic of the left half opened interval\n";
e+=MbOperationNode.CARAC_SEMI_OUVERT_D+"\tCharacteristic of the right half opened interval\n";
e+=MbOperationNode.CARAC_GAUCHE_OUVERT+"\tCharacteristic of the left opened interval\n";
e+=MbOperationNode.CARAC_DROITE_OUVERT+"\tCharacteristic of the right opened interval\n";
e+=MbOperationNode.CARAC_GAUCHE_FERME+"\tCharacteristic of the closed left interval\n";
e+=MbOperationNode.CARAC_DROITE_FERME+"\tCharacteristic of the closed right interval\n";
e+=MbOperationNode.SGN+"\tSignum\n";
e+=MbOperationNode.CARD+"\tElements count\n";
e+=MbOperationNode.INTER+"\tIntersect\n";
e+=MbOperationNode.UNION+"\tUnion\n";
e+=MbOperationNode.COMPL+"\tComplement of\n";
e+=MbOperationNode.INCL+"\tNumeric include\n";
e+=MbOperationNode.NON_INCL+"\tNon numeric include\n";
e+=MbOperationNode.EQ_NUM+"\tNumeric equality\n";
e+=MbOperationNode.NON_EQ_NUM+"\tNumeric difference\n";
//e+=MbOperationNode.VIDE+"\tEmpty set\n";
return e;
}
}
