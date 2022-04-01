package cards.tarot.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class ResultsTarotBeanLinesDeal implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return TarotStandards.getLineDealArray(((ResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getLinesDeal());
    }
}
