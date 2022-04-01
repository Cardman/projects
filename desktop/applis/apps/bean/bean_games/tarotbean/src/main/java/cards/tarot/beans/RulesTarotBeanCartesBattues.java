package cards.tarot.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class RulesTarotBeanCartesBattues implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(((RulesTarotBean) ((TarotBeanStruct)_instance).getInstance()).getCartesBattues());
    }
}
