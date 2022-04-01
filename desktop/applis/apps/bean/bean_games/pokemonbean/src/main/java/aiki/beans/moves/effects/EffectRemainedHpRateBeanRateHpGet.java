package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class EffectRemainedHpRateBeanRateHpGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (EffectRemainedHpRateBean) ((PokemonBeanStruct)_instance).getInstance()).getRateHp(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
