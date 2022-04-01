package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class StatusBeanCatchingRateGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getCatchingRate(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
