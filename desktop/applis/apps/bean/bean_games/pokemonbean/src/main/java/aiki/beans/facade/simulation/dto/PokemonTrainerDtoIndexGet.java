package aiki.beans.facade.simulation.dto;

import aiki.beans.PokemonTrainerDtoStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonTrainerDtoIndexGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( ((PokemonTrainerDtoStruct) _instance).getPokemonTrainerDto()).getIndex());
    }
}
