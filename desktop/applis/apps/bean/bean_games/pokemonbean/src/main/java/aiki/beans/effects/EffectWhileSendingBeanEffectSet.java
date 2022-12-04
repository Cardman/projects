package aiki.beans.effects;

import aiki.beans.EffectWhileSendingWithStatisticStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectWhileSendingBeanEffectSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EffectWhileSendingBean) ((PokemonBeanStruct)_instance).getInstance()).setEffect(((EffectWhileSendingWithStatisticStruct)_args[0]).getEffectPartnerStatus());
        return NaNu.NULL_VALUE;
    }
}
