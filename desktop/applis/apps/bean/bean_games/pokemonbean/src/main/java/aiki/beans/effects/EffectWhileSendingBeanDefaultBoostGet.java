package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class EffectWhileSendingBeanDefaultBoostGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (EffectWhileSendingBean) ((PokemonBeanStruct)_instance).getInstance()).getDefaultBoost());
    }
}
