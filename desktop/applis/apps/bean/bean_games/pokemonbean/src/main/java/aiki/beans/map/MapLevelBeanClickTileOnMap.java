package aiki.beans.map;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MapLevelBeanClickTileOnMap implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (MapLevelBean) ((PokemonBeanStruct)_instance).getInstance()).clickTileOnMap(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
