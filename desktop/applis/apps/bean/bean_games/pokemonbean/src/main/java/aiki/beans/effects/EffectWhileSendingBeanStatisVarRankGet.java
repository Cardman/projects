package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;

public class EffectWhileSendingBeanStatisVarRankGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrByte(((EffectWhileSendingBean) ((PokemonBeanStruct) _instance).getInstance()).getEffectStatisticCommon().getStatisVarRank());
    }
}
