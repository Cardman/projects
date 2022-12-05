package aiki.beans.facade.simulation.dto;

import aiki.beans.PokemonPlayerDtoStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonPlayerDtoIndexGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( ((PokemonPlayerDtoStruct) _instance).getPokemonPlayerDto()).getIndex());
    }
}
