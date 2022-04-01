package aiki.beans;

import aiki.map.pokemon.PokemonPlayer;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonPlayerGetItem implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (PokemonPlayer) ((PkStruct)_instance).getWildPk()).getItem());
    }
}
