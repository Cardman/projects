package aiki.beans.map.elements;

import aiki.beans.*;
import code.bean.nat.*;
public class AreaBeanGetMovesAtLevel implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (AreaBean) ((PokemonBeanStruct)_instance).getInstance()).getMovesAtLevel(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
