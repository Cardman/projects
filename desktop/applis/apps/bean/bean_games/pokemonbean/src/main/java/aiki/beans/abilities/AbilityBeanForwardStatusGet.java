package aiki.beans.abilities;

import aiki.beans.*;
import code.bean.nat.*;
public class AbilityBeanForwardStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStrKey(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getForwardStatus());
    }
}
