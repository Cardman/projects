package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EvolvingStoneBeanGetTrPokemon implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EvolvingStoneBean) ((PokemonBeanStruct)_instance).getInstance()).getTrPokemon(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
