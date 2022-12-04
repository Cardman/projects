package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class BoostBeanIsBall implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (BoostBean) ((PokemonBeanStruct)_instance).getInstance()).isBall(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
