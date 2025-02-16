package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectGlobalBeanEfficiencyMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getWeatherTypeRateMap(( (EffectGlobalBean) ((PokemonBeanStruct)_instance).getInstance()).getEfficiencyMoves());
    }
}
