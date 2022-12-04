package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class HealingStatusBeanClickStatus implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (HealingStatusBean) ((PokemonBeanStruct)_instance).getInstance()).clickStatus(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
