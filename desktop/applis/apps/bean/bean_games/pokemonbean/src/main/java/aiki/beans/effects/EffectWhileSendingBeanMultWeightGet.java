package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class EffectWhileSendingBeanMultWeightGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (EffectWhileSendingBean) ((PokemonBeanStruct)_instance).getInstance()).getMultWeight());
    }
}
