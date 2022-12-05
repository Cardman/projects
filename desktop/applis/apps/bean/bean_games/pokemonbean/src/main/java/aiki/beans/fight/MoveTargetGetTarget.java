package aiki.beans.fight;

import code.bean.nat.*;
import code.bean.nat.*;
public class MoveTargetGetTarget implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new TargetCoordsStruct(( ((MoveTargetStruct) _instance).getMoveTarget()).getTarget());
    }
}
