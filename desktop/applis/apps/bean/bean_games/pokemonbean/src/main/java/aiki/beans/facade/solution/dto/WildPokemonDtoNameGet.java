package aiki.beans.facade.solution.dto;

import aiki.beans.WildPokemonDtoStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class WildPokemonDtoNameGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( ((WildPokemonDtoStruct) _instance).getInstance()).getName());
    }
}
