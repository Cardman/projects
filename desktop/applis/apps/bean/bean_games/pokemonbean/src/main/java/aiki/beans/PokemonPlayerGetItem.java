package aiki.beans;

import aiki.map.pokemon.PokemonPlayer;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonPlayerGetItem implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (PokemonPlayer) ((PkStruct)_instance).getWildPk()).getItem());
    }
}
