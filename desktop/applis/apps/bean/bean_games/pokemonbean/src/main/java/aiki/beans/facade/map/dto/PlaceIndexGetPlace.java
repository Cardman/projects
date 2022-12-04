package aiki.beans.facade.map.dto;

import aiki.beans.PlaceIndexStruct;
import aiki.beans.PlaceStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class PlaceIndexGetPlace implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new PlaceStruct(( ((PlaceIndexStruct) _instance).getPlaceIndex()).getPlace());
    }
}
