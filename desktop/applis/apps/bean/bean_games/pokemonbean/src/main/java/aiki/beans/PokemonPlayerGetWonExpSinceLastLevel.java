package aiki.beans;

import aiki.map.pokemon.PokemonPlayer;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonPlayerGetWonExpSinceLastLevel implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (PokemonPlayer) ((PkStruct)_instance).getWildPk()).getWonExpSinceLastLevel(), BeanNatCommonLgNames.TYPE_RATE);
    }
}
