package aiki.beans.facade.fight;

import aiki.beans.fight.KeyHypothesisStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class KeyHypothesisGetMove implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( ((KeyHypothesisStruct) _instance).getInstance()).getMove());
    }
}
