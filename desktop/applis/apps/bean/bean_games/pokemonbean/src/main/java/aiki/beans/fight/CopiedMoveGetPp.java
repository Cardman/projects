package aiki.beans.fight;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class CopiedMoveGetPp implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( ((CopiedMoveStruct) _instance).getCopiedMove()).getPp());
    }
}
