package aiki.beans.fight;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class MoveTargetGetMove implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( ((MoveTargetStruct) _instance).getInstance()).getMove());
    }
}
