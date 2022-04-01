package cards.tarot.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class DetailsResultsTarotBeanSmall implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(((DetailsResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getSmall());
    }
}
