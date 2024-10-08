package aiki.beans.map.elements;

import aiki.beans.*;
import code.bean.nat.*;
public class LegendaryPokemonBeanGetImage implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (LegendaryPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getImage());
    }
}
