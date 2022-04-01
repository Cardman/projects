package aiki.beans.pokemon.evolutions;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class EvolutionBeanBaseSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (EvolutionBean) ((PokemonBeanStruct)_instance).getInstance()).setBase(NumParsers.getString(_args[0]).getInstance());
        return NullStruct.NULL_VALUE;
    }
}
