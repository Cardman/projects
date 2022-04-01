package aiki.beans.pokemon.evolutions;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;
public class EvolutionHappinessBeanMinGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new LongStruct(( (EvolutionHappinessBean) ((PokemonBeanStruct)_instance).getInstance()).getMin());
    }
}
