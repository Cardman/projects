package aiki.beans.facade.map.dto;

import aiki.beans.PlaceIndexStruct;
import aiki.beans.PlaceStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class PlaceIndexGetPlace implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new PlaceStruct(( ((PlaceIndexStruct) _instance).getPlaceIndex()).getPlace(),PokemonStandards.TYPE_PLACE);
    }
}
