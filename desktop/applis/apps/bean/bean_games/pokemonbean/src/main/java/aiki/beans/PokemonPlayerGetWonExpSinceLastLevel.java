package aiki.beans;

import aiki.map.pokemon.PokemonPlayer;
import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
public class PokemonPlayerGetWonExpSinceLastLevel implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct(( (PokemonPlayer) ((PkStruct)_instance).getWildPk()).getWonExpSinceLastLevel());
    }
}
