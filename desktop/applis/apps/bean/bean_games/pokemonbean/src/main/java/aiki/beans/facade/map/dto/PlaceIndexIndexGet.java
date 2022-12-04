package aiki.beans.facade.map.dto;

import aiki.beans.PlaceIndexStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PlaceIndexIndexGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( ((PlaceIndexStruct) _instance).getPlaceIndex()).getIndex());
    }
}
