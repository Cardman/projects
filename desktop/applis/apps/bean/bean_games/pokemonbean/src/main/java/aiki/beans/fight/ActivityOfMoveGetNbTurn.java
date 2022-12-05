package aiki.beans.fight;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class ActivityOfMoveGetNbTurn implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( ((ActivityOfMoveStruct) _instance).getActivityOfMove()).getNbTurn());
    }
}
