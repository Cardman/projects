package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class HealingItemBeanIsBall implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (HealingItemBean) ((PokemonBeanStruct)_instance).getInstance()).isBall(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
