package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;

public class EffectStatisticBeanStatisVarRankGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrLongStat(((EffectStatisticBean) ((PokemonBeanStruct) _instance).getInstance()).getEffectStatisticCommon().getStatisVarRank());
    }
}
