package aiki.beans.fight;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class AnticipationGetTargetPosition implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new TargetCoordsStruct(( ((AnticipationStruct) _instance).getInstance()).getTargetPosition());
    }
}
