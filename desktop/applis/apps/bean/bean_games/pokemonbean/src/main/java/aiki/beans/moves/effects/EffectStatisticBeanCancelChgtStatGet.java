package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;

public class EffectStatisticBeanCancelChgtStatGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getValues(((EffectStatisticBean) ((PokemonBeanStruct) _instance).getInstance()).getEffectStatisticCommon().getCancelChgtStat());
    }
}
