package aiki.beans.effects;

import aiki.beans.EffectWhileSendingWithStatisticStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class EffectWhileSendingBeanEffectSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (EffectWhileSendingBean) ((PokemonBeanStruct)_instance).getInstance()).setEffect(((EffectWhileSendingWithStatisticStruct)_args[0]).getEffectPartnerStatus());
        return NullStruct.NULL_VALUE;
    }
}
