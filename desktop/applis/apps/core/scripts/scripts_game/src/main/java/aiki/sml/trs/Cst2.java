package aiki.sml.trs;
import code.maths.litteral.*;
import code.util.*;
import aiki.fight.pokemon.enums.*;
public final class Cst2{
private Cst2(){}
static IdMap<ExpType,String> ex(){
IdMap<ExpType,String> e=new IdMap<ExpType,String>(new CollCapacity(6));
e.addEntry(ExpType.P,MbOperationNode.CARAC_FERME+"("+Cst.NIV+",1,1)+"+MbOperationNode.CARAC_DROITE_FERME+"("+Cst.NIV+",2)*(6/5*"+MbOperationNode.PUIS+"("+Cst.NIV+",3)-15*"+MbOperationNode.PUIS+"("+Cst.NIV+",2)+100*"+Cst.NIV+"-140)");
e.addEntry(ExpType.L,"5/4*"+MbOperationNode.PUIS+"("+Cst.NIV+",3)");
e.addEntry(ExpType.E,MbOperationNode.CARAC_FERME+"("+Cst.NIV+",1,50)*"+MbOperationNode.PUIS+"("+Cst.NIV+",3)*"+MbOperationNode.DIV_FCT+"(100-"+Cst.NIV+",50)+"+MbOperationNode.CARAC_FERME+"("+Cst.NIV+",51,68)*"+MbOperationNode.PUIS+"("+Cst.NIV+",3)*"+MbOperationNode.DIV_FCT+"((150-"+Cst.NIV+"),100)+"+MbOperationNode.CARAC_FERME+"("+Cst.NIV+",69,98)*"+MbOperationNode.PUIS+"("+Cst.NIV+",3)*"+MbOperationNode.DIV_FCT+"("+MbOperationNode.ENT+"("+MbOperationNode.DIV_FCT+"((1911-10*"+Cst.NIV+"),3)),500)+"+MbOperationNode.CARAC_FERME+"("+Cst.NIV+",99,110)*"+MbOperationNode.PUIS+"("+Cst.NIV+",3)*"+MbOperationNode.DIV_FCT+"((160-"+Cst.NIV+"),100)+(363*"+MbOperationNode.PUIS+"("+Cst.NIV+",2)-75027*"+Cst.NIV+"+4526170)*"+MbOperationNode.CARAC_DROITE_FERME+"("+Cst.NIV+",111)");
e.addEntry(ExpType.F,MbOperationNode.CARAC_FERME+"("+Cst.NIV+",1,15)*"+MbOperationNode.PUIS+"("+Cst.NIV+",3)*"+MbOperationNode.DIV_FCT+"(24+"+MbOperationNode.ENT+"("+MbOperationNode.DIV_FCT+"("+Cst.NIV+"+1,3)),50)+"+MbOperationNode.CARAC_FERME+"("+Cst.NIV+",16,35)*"+MbOperationNode.PUIS+"("+Cst.NIV+",3)*"+MbOperationNode.DIV_FCT+"(14+"+Cst.NIV+",50)+"+MbOperationNode.CARAC_DROITE_FERME+"("+Cst.NIV+",36)*"+MbOperationNode.PUIS+"("+Cst.NIV+",3)*"+MbOperationNode.DIV_FCT+"(32+"+MbOperationNode.ENT+"("+MbOperationNode.DIV_FCT+"("+Cst.NIV+",2)),50)");
e.addEntry(ExpType.M,MbOperationNode.PUIS+"("+Cst.NIV+",3)");
e.addEntry(ExpType.R,"4/5*"+MbOperationNode.PUIS+"("+Cst.NIV+",3)");
return e;
}
}
