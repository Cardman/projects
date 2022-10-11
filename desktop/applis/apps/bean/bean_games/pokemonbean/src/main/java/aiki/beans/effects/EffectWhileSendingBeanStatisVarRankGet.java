package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;

public class EffectWhileSendingBeanStatisVarRankGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getStrByte(((EffectWhileSendingBean) ((PokemonBeanStruct) _instance).getInstance()).getEffectStatisticCommon().getStatisVarRank());
    }
}
