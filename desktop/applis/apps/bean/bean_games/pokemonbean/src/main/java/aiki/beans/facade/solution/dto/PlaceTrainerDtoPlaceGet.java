package aiki.beans.facade.solution.dto;

import aiki.beans.PlaceTrainerDtoStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class PlaceTrainerDtoPlaceGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( ((PlaceTrainerDtoStruct) _instance).getInstance()).getPlace());
    }
}
