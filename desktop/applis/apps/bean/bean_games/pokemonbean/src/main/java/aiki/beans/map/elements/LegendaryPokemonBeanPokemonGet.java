package aiki.beans.map.elements;

import aiki.beans.PkStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class LegendaryPokemonBeanPokemonGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new PkStruct(( (LegendaryPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getPokemon());
    }
}
