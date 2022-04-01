package aiki.beans.facade.simulation.dto;

import aiki.beans.PokemonPlayerDtoStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonPlayerDtoIndexGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( ((PokemonPlayerDtoStruct) _instance).getInstance()).getIndex());
    }
}
