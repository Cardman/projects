package cards.tarot.beans;

import cards.consts.beans.LineDealStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public final class ResultsTarotBeanLinesDeal implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return LineDealStruct.getLineDealArray(((ResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getLinesDeal());
    }
}
