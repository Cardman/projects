package aiki.beans.fight;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class ActivityOfMoveIsIncrementCount implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( ((ActivityOfMoveStruct) _instance).getActivityOfMove()).isIncrementCount());
    }
}
