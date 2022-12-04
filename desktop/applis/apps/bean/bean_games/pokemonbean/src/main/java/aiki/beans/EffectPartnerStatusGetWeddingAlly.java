package aiki.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectPartnerStatusGetWeddingAlly implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( ((EffectPartnerStatusStruct) _instance).getEffectPartnerStatus()).getWeddingAlly());
    }
}
