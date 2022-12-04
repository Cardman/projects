package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectWhileSendingBeanStatisticGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (EffectWhileSendingBean) ((PokemonBeanStruct)_instance).getInstance()).getStatistic());
    }
}
