package aiki.beans.pokemon.evolutions;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EvolutionBeanClickEvo implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EvolutionBean) ((PokemonBeanStruct)_instance).getInstance()).clickEvo(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
