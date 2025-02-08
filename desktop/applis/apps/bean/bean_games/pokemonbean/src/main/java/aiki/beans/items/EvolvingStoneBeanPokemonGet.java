package aiki.beans.items;

import aiki.beans.*;
import code.bean.nat.*;
public class EvolvingStoneBeanPokemonGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (EvolvingStoneBean) ((PokemonBeanStruct)_instance).getInstance()).getPokemon());
    }
}
