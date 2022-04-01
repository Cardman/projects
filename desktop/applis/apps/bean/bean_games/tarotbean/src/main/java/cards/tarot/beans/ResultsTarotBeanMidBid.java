package cards.tarot.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class ResultsTarotBeanMidBid implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(((ResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).midBid());
    }
}
