package aiki.beans.map.elements;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class LegendaryPokemonBeanGetGender implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (LegendaryPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getGender());
    }
}
