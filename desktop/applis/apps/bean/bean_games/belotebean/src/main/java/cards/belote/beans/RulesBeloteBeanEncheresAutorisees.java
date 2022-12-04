package cards.belote.beans;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public final class RulesBeloteBeanEncheresAutorisees implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(((RulesBeloteBean) ((BeloteBeanStruct)_instance).getInstance()).getEncheresAutorisees());
    }
}
