package cards.belote.beans;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public final class RulesBeloteBeanAnnoncesAutorisees implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(((RulesBeloteBean) ((BeloteBeanStruct)_instance).getInstance()).getAnnoncesAutorisees());
    }
}
