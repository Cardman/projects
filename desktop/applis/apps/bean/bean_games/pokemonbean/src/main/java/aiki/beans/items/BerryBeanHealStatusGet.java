package aiki.beans.items;

import aiki.beans.*;
import code.bean.nat.*;
public class BerryBeanHealStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (BerryBean) ((PokemonBeanStruct)_instance).getInstance()).getHealStatus());
    }
}
