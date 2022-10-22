package aiki.beans.fight;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class AnticipationIsIncrementing implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( ((AnticipationStruct) _instance).getInstance()).isIncrementing());
    }
}