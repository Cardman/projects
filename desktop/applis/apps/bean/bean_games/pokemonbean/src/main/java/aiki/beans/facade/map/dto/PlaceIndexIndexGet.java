package aiki.beans.facade.map.dto;

import aiki.beans.PlaceIndexStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class PlaceIndexIndexGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( ((PlaceIndexStruct) _instance).getPlaceIndex()).getIndex());
    }
}
