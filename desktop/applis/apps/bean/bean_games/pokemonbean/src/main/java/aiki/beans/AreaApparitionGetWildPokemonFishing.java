package aiki.beans;

import code.bean.nat.*;
import code.bean.nat.*;
public class AreaApparitionGetWildPokemonFishing implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getWildPkArray(( ((AreaApparitionStruct) _instance).getWildPk()).getWildPokemonFishing());
    }
}
