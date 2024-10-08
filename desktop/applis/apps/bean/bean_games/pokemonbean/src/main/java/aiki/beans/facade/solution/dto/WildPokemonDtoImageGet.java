package aiki.beans.facade.solution.dto;

import aiki.beans.*;
import code.bean.nat.*;
public class WildPokemonDtoImageGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( ((WildPokemonDtoStruct) _instance).getWildPokemonDto()).getImage());
    }
}
