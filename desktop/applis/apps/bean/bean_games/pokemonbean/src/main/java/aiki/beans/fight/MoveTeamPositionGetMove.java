package aiki.beans.fight;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MoveTeamPositionGetMove implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( ((MoveTeamPositionStruct) _instance).getMoveTeamPosition()).getMove());
    }
}
