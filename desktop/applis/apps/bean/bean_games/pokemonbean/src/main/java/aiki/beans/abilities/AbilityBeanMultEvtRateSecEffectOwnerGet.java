package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class AbilityBeanMultEvtRateSecEffectOwnerGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getMultEvtRateSecEffectOwner());
    }
}
