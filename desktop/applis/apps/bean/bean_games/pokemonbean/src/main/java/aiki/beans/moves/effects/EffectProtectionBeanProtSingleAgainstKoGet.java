package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class EffectProtectionBeanProtSingleAgainstKoGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (EffectProtectionBean) ((PokemonBeanStruct)_instance).getInstance()).getProtSingleAgainstKo());
    }
}
