package aiki.beans.facade.fight;

import aiki.beans.fight.KeyHypothesisStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class KeyHypothesisIsBelongsToUser implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( ((KeyHypothesisStruct) _instance).getInstance()).isBelongsToUser());
    }
}
