package aiki.beans;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class EfficiencyRateGetEff implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( ((EfficiencyRateStruct) _instance).getInstance()).getEff(), BeanNatCommonLgNames.TYPE_RATE);
    }
}
