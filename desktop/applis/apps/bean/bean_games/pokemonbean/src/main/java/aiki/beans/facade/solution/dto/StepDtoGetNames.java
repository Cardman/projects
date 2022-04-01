package aiki.beans.facade.solution.dto;

import aiki.beans.PokemonStandards;
import aiki.beans.StepDtoStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class StepDtoGetNames implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getPlTr(( ((StepDtoStruct) _instance).getInstance()).getNames());
    }
}
