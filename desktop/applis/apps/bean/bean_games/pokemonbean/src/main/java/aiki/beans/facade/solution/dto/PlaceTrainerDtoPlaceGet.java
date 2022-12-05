package aiki.beans.facade.solution.dto;

import aiki.beans.PlaceTrainerDtoStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PlaceTrainerDtoPlaceGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( ((PlaceTrainerDtoStruct) _instance).getPlaceTrainerDto()).getPlace());
    }
}
