package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectMultUsedMovePowerBeanMultMovePowerFctTypeGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrRate(( (EffectMultMovePowerBean) ((PokemonBeanStruct)_instance).getInstance()).getMultMovePowerFctType());
    }
}
