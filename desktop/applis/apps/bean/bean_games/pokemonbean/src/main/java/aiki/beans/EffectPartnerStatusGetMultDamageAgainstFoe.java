package aiki.beans;

import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
public class EffectPartnerStatusGetMultDamageAgainstFoe implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct(( ((EffectPartnerStatusStruct) _instance).getEffectPartnerStatus()).getMultDamageAgainstFoe());
    }
}
