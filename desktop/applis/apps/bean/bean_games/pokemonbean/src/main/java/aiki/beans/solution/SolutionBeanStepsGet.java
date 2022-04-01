package aiki.beans.solution;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class SolutionBeanStepsGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getSteDto(( (SolutionBean) ((PokemonBeanStruct)_instance).getInstance()).getSteps());
    }
}
