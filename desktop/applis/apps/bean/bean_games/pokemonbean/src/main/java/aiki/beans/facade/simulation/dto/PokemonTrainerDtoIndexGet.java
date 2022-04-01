package aiki.beans.facade.simulation.dto;

import aiki.beans.PokemonTrainerDtoStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonTrainerDtoIndexGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( ((PokemonTrainerDtoStruct) _instance).getInstance()).getIndex());
    }
}
