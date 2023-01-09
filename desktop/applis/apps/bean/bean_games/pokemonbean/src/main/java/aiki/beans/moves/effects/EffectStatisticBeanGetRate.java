package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
import code.bean.nat.*;

public class EffectStatisticBeanGetRate implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(((EffectStatisticBean) ((PokemonBeanStruct) _instance).getInstance()).getEffectStatisticCommon().getRate(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
