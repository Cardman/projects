package aiki.beans.map;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MapBeanIsMultiLayer implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (MapBean) ((PokemonBeanStruct)_instance).getInstance()).isMultiLayer(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
