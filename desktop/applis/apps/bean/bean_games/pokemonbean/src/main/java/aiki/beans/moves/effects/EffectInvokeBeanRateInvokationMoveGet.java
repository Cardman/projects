package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class EffectInvokeBeanRateInvokationMoveGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (EffectInvokeBean) ((PokemonBeanStruct)_instance).getInstance()).getRateInvokationMove(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
