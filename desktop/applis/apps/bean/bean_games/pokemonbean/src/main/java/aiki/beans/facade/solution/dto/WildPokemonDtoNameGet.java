package aiki.beans.facade.solution.dto;

import aiki.beans.WildPokemonDtoStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class WildPokemonDtoNameGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( ((WildPokemonDtoStruct) _instance).getInstance()).getName());
    }
}
