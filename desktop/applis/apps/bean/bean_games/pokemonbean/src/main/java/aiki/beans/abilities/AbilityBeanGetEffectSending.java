package aiki.beans.abilities;

import aiki.beans.EffectWhileSendingWithStatisticStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class AbilityBeanGetEffectSending implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new EffectWhileSendingWithStatisticStruct(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getEffectSending());
    }
}
