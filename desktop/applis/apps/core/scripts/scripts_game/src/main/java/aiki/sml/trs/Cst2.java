package aiki.sml.trs;
import aiki.sml.init.*;
import code.maths.litteral.*;
import code.util.*;
import aiki.fight.pokemon.enums.*;
public final class Cst2 extends CstIgame {
private Cst2(){}
public static IdMap<ExpType,String> ex(){
IdMap<ExpType,String> e=new IdMap<ExpType,String>(new CollCapacity(6));
e.addEntry(ExpType.P,MbOperationNode.CARAC_FERME+LP+Cst.NIV+OC+R_1+OC+R_1+RP+OP+MbOperationNode.CARAC_DROITE_FERME+LP+Cst.NIV+OC+R_2+RP+OM+LP+R_6_5+OM+MbOperationNode.PUIS+LP+Cst.NIV+OC+R_3+RP+OD+R_15+OM+MbOperationNode.PUIS+LP+Cst.NIV+OC+R_2+RP+OP+R_100+OM+Cst.NIV+OD+R_140+RP);
e.addEntry(ExpType.L,R_5_4+OM+MbOperationNode.PUIS+LP+Cst.NIV+OC+R_3+RP);
e.addEntry(ExpType.E,MbOperationNode.CARAC_FERME+LP+Cst.NIV+OC+R_1+OC+R_50+RP+OM+MbOperationNode.PUIS+LP+Cst.NIV+OC+R_3+RP+OM+MbOperationNode.DIV_FCT+LP+R_100+OD+Cst.NIV+OC+R_50+RP+OP+MbOperationNode.CARAC_FERME+LP+Cst.NIV+OC+R_51+OC+R_68+RP+OM+MbOperationNode.PUIS+LP+Cst.NIV+OC+R_3+RP+OM+MbOperationNode.DIV_FCT+LP+LP+R_150+OD+Cst.NIV+RP+OC+R_100+RP+OP+MbOperationNode.CARAC_FERME+LP+Cst.NIV+OC+R_69+OC+R_98+RP+OM+MbOperationNode.PUIS+LP+Cst.NIV+OC+R_3+RP+OM+MbOperationNode.DIV_FCT+LP+MbOperationNode.ENT+LP+MbOperationNode.DIV_FCT+LP+LP+R_1911+OD+R_10+OM+Cst.NIV+RP+OC+R_3+RP+RP+OC+R_500+RP+OP+MbOperationNode.CARAC_FERME+LP+Cst.NIV+OC+R_99+OC+R_110+RP+OM+MbOperationNode.PUIS+LP+Cst.NIV+OC+R_3+RP+OM+MbOperationNode.DIV_FCT+LP+LP+R_160+OD+Cst.NIV+RP+OC+R_100+RP+OP+LP+R_363+OM+MbOperationNode.PUIS+LP+Cst.NIV+OC+R_2+RP+OD+R_75027+OM+Cst.NIV+OP+R_4526170+RP+OM+MbOperationNode.CARAC_DROITE_FERME+LP+Cst.NIV+OC+R_111+RP);
e.addEntry(ExpType.F,MbOperationNode.CARAC_FERME+LP+Cst.NIV+OC+R_1+OC+R_15+RP+OM+MbOperationNode.PUIS+LP+Cst.NIV+OC+R_3+RP+OM+MbOperationNode.DIV_FCT+LP+R_24+OP+MbOperationNode.ENT+LP+MbOperationNode.DIV_FCT+LP+Cst.NIV+OP+R_1+OC+R_3+RP+RP+OC+R_50+RP+OP+MbOperationNode.CARAC_FERME+LP+Cst.NIV+OC+R_16+OC+R_35+RP+OM+MbOperationNode.PUIS+LP+Cst.NIV+OC+R_3+RP+OM+MbOperationNode.DIV_FCT+LP+R_14+OP+Cst.NIV+OC+R_50+RP+OP+MbOperationNode.CARAC_DROITE_FERME+LP+Cst.NIV+OC+R_36+RP+OM+MbOperationNode.PUIS+LP+Cst.NIV+OC+R_3+RP+OM+MbOperationNode.DIV_FCT+LP+R_32+OP+MbOperationNode.ENT+LP+MbOperationNode.DIV_FCT+LP+Cst.NIV+OC+R_2+RP+RP+OC+R_50+RP);
e.addEntry(ExpType.M,MbOperationNode.PUIS+LP+Cst.NIV+OC+R_3+RP);
e.addEntry(ExpType.R,R_4_5+OM+MbOperationNode.PUIS+LP+Cst.NIV+OC+R_3+RP);
return e;
}
}
