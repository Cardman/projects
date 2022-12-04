package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class HealingPpBeanLimitedPpMove implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (HealingPpBean) ((PokemonBeanStruct)_instance).getInstance()).limitedPpMove());
    }
}
