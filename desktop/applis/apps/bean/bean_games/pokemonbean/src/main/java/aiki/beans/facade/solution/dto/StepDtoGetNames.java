package aiki.beans.facade.solution.dto;

import aiki.beans.PokemonStandards;
import aiki.beans.StepDtoStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class StepDtoGetNames implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getPlTr(( ((StepDtoStruct) _instance).getStepDto()).getNames());
    }
}
