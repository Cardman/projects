package aiki.sml.trs;
import code.util.*;
import aiki.fight.pokemon.enums.*;
public final class Cst2{
private Cst2(){}
static IdMap<ExpType,String> ex(){
IdMap<ExpType,String> e=new IdMap<ExpType,String>(new CollCapacity(6));
e.addEntry(ExpType.P,"caracferme(VAR__NIVEAU,1,1)+caracdroiteferme(VAR__NIVEAU,2)*(6/5*puis(VAR__NIVEAU,3)-15*puis(VAR__NIVEAU,2)+100*VAR__NIVEAU-140)");
e.addEntry(ExpType.L,"5/4*puis(VAR__NIVEAU,3)");
e.addEntry(ExpType.E,"caracferme(VAR__NIVEAU,1,50)*puis(VAR__NIVEAU,3)*div(100-VAR__NIVEAU,50)+caracferme(VAR__NIVEAU,51,68)*puis(VAR__NIVEAU,3)*div((150-VAR__NIVEAU),100)+caracferme(VAR__NIVEAU,69,98)*puis(VAR__NIVEAU,3)*div(ent(div((1911-10*VAR__NIVEAU),3)),500)+caracferme(VAR__NIVEAU,99,110)*puis(VAR__NIVEAU,3)*div((160-VAR__NIVEAU),100)+(363*puis(VAR__NIVEAU,2)-75027*VAR__NIVEAU+4526170)*caracdroiteferme(VAR__NIVEAU,111)");
e.addEntry(ExpType.F,"caracferme(VAR__NIVEAU,1,15)*puis(VAR__NIVEAU,3)*div(24+ent(div(VAR__NIVEAU+1,3)),50)+caracferme(VAR__NIVEAU,16,35)*puis(VAR__NIVEAU,3)*div(14+VAR__NIVEAU,50)+caracdroiteferme(VAR__NIVEAU,36)*puis(VAR__NIVEAU,3)*div(32+ent(div(VAR__NIVEAU,2)),50)");
e.addEntry(ExpType.M,"puis(VAR__NIVEAU,3)");
e.addEntry(ExpType.R,"4/5*puis(VAR__NIVEAU,3)");
return e;
}
}
