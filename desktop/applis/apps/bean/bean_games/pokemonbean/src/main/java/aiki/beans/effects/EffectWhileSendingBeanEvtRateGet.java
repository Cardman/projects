package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class EffectWhileSendingBeanEvtRateGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (EffectWhileSendingBean) ((PokemonBeanStruct)_instance).getInstance()).getEvtRate(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
