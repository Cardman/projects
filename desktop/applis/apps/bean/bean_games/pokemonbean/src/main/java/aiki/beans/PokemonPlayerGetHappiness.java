package aiki.beans;

import aiki.map.pokemon.PokemonPlayer;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonPlayerGetHappiness implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (PokemonPlayer) ((PkStruct)_instance).getWildPk()).getHappiness());
    }
}
