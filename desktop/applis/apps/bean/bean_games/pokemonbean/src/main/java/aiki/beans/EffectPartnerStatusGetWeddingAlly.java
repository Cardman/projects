package aiki.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class EffectPartnerStatusGetWeddingAlly implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( ((EffectPartnerStatusStruct) _instance).getEffectPartnerStatus()).getWeddingAlly());
    }
}
