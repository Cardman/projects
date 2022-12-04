package aiki.beans.map.elements;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class LegendaryPokemonBeanGetLevel implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (LegendaryPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getLevel());
    }
}
