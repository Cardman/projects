package aiki.beans.map.elements;

import aiki.beans.PkStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class LegendaryPokemonBeanPokemonGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new PkStruct(( (LegendaryPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getPokemon());
    }
}
