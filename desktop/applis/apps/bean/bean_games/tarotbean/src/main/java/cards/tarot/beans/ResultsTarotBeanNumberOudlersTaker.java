package cards.tarot.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class ResultsTarotBeanNumberOudlersTaker implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(((ResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getNumberOudlersTaker());
    }
}
