package aiki.beans.facade.fight;

import aiki.beans.fight.KeyHypothesisStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;

public class KeyHypothesisGetDamageSecond implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( ((KeyHypothesisStruct) _instance).getInstance()).getDamageSecond());
    }
}