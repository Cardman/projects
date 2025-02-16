package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectCommonStatisticsBeanCommonValueGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStrOnly(( (EffectCommonStatisticsBean) ((PokemonBeanStruct)_instance).getInstance()).getCommonValue());
    }
}
