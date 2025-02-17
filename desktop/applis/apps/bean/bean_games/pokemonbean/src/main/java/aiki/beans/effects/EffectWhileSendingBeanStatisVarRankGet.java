package aiki.beans.effects;

import aiki.beans.*;
import code.bean.nat.*;

public class EffectWhileSendingBeanStatisVarRankGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrLongStat(((EffectWhileSendingBean) ((PokemonBeanStruct) _instance).getInstance()).getEffectStatisticCommon().getStatisVarRank());
    }
}
