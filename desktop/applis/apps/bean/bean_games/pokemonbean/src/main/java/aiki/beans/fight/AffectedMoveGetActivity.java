package aiki.beans.fight;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class AffectedMoveGetActivity implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new ActivityOfMoveStruct(( ((AffectedMoveStruct) _instance).getInstance()).getActivity());
    }
}
