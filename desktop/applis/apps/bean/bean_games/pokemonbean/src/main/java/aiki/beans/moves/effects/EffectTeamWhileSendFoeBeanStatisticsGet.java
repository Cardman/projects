package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectTeamWhileSendFoeBeanStatisticsGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrLong(( (EffectTeamWhileSendFoeBean) ((PokemonBeanStruct)_instance).getInstance()).getStatistics());
    }
}
