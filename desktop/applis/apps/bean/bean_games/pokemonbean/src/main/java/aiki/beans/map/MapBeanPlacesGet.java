package aiki.beans.map;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class MapBeanPlacesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getPlInd(( (MapBean) ((PokemonBeanStruct)_instance).getInstance()).getPlaces());
    }
}
