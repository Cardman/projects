package aiki.beans.facade.dto;

import aiki.beans.PkLineStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonLineEvolutionsGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( ((PkLineStruct) _instance).getWildPk()).getEvolutions());
    }
}
