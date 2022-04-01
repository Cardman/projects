package aiki.beans;

import aiki.map.pokemon.PokemonPlayer;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonPlayerGetHappiness implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (PokemonPlayer) ((PkStruct)_instance).getWildPk()).getHappiness());
    }
}
