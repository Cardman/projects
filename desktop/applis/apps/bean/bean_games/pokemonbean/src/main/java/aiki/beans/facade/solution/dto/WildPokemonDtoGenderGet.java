package aiki.beans.facade.solution.dto;

import aiki.beans.WildPokemonDtoStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class WildPokemonDtoGenderGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( ((WildPokemonDtoStruct) _instance).getWildPokemonDto()).getGender());
    }
}
