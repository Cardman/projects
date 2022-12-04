package cards.tarot.beans;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public final class RulesTarotBeanMiseres implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(((RulesTarotBean) ((TarotBeanStruct)_instance).getInstance()).getMiseres());
    }
}
