package aiki.beans.fight;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class AffectedMoveGetMove implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( ((AffectedMoveStruct) _instance).getInstance()).getMove());
    }
}
