package cards.tarot.beans;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class RulesTarotBeanMiseres implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(((RulesTarotBean) ((TarotBeanStruct)_instance).getInstance()).getMiseres());
    }
}
