package aiki.beans;

import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class BoostHpRateGetHpRate implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( ((BoostHpRateStruct) _instance).getInstance()).getHpRate());
    }
}
