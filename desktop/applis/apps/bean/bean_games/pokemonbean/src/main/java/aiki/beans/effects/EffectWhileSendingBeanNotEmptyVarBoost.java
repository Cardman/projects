package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class EffectWhileSendingBeanNotEmptyVarBoost implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(((EffectWhileSendingBean) ((PokemonBeanStruct) _instance).getInstance()).getEffectStatisticCommon().notEmptyVarBoost());
    }
}
