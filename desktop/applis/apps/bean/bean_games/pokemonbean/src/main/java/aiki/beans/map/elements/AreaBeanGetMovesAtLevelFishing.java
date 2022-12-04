package aiki.beans.map.elements;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AreaBeanGetMovesAtLevelFishing implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (AreaBean) ((PokemonBeanStruct)_instance).getInstance()).getMovesAtLevelFishing(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
