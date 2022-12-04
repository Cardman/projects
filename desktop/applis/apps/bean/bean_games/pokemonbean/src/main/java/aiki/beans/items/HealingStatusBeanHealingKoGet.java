package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class HealingStatusBeanHealingKoGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (HealingStatusBean) ((PokemonBeanStruct)_instance).getInstance()).getHealingKo());
    }
}
