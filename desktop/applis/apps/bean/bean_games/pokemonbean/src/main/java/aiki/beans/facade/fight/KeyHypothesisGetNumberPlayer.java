package aiki.beans.facade.fight;

import aiki.beans.fight.KeyHypothesisStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public class KeyHypothesisGetNumberPlayer implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( ((KeyHypothesisStruct) _instance).getInstance()).getNumberPlayer());
    }
}
