package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectTeamBeanMultStatisticFoeGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrRateVal(( (EffectTeamBean) ((PokemonBeanStruct)_instance).getInstance()).getMultStatisticFoe());
    }
}
