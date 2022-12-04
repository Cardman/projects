package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectCommonStatisticsBeanMapVarsCommonStatisticsGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStr(( (EffectCommonStatisticsBean) ((PokemonBeanStruct)_instance).getInstance()).getMapVarsCommonStatistics());
    }
}
