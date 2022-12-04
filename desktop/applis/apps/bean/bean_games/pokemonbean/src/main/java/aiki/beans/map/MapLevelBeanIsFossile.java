package aiki.beans.map;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MapLevelBeanIsFossile implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (MapLevelBean) ((PokemonBeanStruct)_instance).getInstance()).isFossile(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
