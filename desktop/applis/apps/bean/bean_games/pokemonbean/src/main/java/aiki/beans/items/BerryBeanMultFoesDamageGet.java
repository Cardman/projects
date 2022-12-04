package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class BerryBeanMultFoesDamageGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getEffRateStr(( (BerryBean) ((PokemonBeanStruct)_instance).getInstance()).getMultFoesDamage());
    }
}
