package aiki.beans.abilities;

import aiki.beans.*;
import code.bean.nat.*;
public class AbilityBeanMultStatIfCatGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getWcRateMap(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getMultStatIfCat());
    }
}
