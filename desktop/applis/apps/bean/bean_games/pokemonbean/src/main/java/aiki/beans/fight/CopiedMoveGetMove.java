package aiki.beans.fight;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class CopiedMoveGetMove implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( ((CopiedMoveStruct) _instance).getCopiedMove()).getMove());
    }
}
