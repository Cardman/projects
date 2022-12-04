package aiki.beans.map;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MapBeanClickLevelZero implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (MapBean) ((PokemonBeanStruct)_instance).getInstance()).clickLevel(NaPa.convertToNumber(_args[0]).intStruct(),0));
    }
}
