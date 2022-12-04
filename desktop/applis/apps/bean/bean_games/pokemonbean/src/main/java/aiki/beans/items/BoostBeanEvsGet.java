package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class BoostBeanEvsGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStaShort(( (BoostBean) ((PokemonBeanStruct)_instance).getInstance()).getEvs());
    }
}
