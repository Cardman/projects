package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectComboBeanMultStatisticFoeGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStaRate(( (EffectComboBean) ((PokemonBeanStruct)_instance).getInstance()).getMultStatisticFoe());
    }
}
