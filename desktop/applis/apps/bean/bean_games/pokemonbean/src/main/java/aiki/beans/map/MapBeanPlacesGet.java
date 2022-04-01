package aiki.beans.map;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class MapBeanPlacesGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getPlInd(( (MapBean) ((PokemonBeanStruct)_instance).getInstance()).getPlaces());
    }
}
