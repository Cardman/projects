package aiki.beans.map.elements;

import aiki.beans.*;
import code.bean.nat.*;
public class LegendaryPokemonBeanGetMovesAtLevel implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (LegendaryPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getMovesAtLevel());
    }
}
