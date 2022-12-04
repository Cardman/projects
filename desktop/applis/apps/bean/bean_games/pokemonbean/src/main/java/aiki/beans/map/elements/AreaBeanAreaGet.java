package aiki.beans.map.elements;

import aiki.beans.AreaApparitionStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class AreaBeanAreaGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new AreaApparitionStruct(( (AreaBean) ((PokemonBeanStruct)_instance).getInstance()).getArea());
    }
}
