package aiki.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class AreaApparitionGetWildPokemon implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getWildPkArray(( ((AreaApparitionStruct) _instance).getWildPk()).getWildPokemon());
    }
}
