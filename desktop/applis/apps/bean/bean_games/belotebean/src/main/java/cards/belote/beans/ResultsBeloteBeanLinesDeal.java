package cards.belote.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class ResultsBeloteBeanLinesDeal implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeloteStandards.getLineDealArray(((ResultsBeloteBean) ((BeloteBeanStruct)_instance).getInstance()).getLinesDeal());
    }
}
