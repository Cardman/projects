package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class EffectEndRoundFoeBeanInflictedRateHpTargetGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (EffectEndRoundFoeBean) ((PokemonBeanStruct)_instance).getInstance()).getInflictedRateHpTarget());
    }
}
