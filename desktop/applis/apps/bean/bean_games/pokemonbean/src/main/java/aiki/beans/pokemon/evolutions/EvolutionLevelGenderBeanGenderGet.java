package aiki.beans.pokemon.evolutions;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EvolutionLevelGenderBeanGenderGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EvolutionLevelGenderBean) ((PokemonBeanStruct)_instance).getInstance()).getGender());
    }
}
