package cards.belote.beans;

import cards.consts.beans.LineDealStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public final class ResultsBeloteBeanLinesDeal implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return LineDealStruct.getLineDealArray(((ResultsBeloteBean) ((BeloteBeanStruct)_instance).getInstance()).getLinesDeal());
    }
}
