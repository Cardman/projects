package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
import code.bean.nat.*;

public class EffectWhileSendingBeanGetRate implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(((EffectWhileSendingBean) ((PokemonBeanStruct) _instance).getInstance()).getEffectStatisticCommon().getRate(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
