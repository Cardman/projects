package aiki.beans.solution;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class SolutionBeanStepsGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getSteDto(( (SolutionBean) ((PokemonBeanStruct)_instance).getInstance()).getSteps());
    }
}
