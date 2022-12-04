package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class HealingItemBeanClickHappiness implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (HealingItemBean) ((PokemonBeanStruct)_instance).getInstance()).clickHappiness(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
