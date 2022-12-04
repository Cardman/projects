package aiki.beans.map.elements;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class LegendaryPokemonBeanGetMovesAtLevel implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (LegendaryPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getMovesAtLevel());
    }
}
