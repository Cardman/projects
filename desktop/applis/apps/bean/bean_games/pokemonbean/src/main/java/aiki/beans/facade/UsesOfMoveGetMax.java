package aiki.beans.facade;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class UsesOfMoveGetMax implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( ((UsesOfMoveStruct) _instance).getUsesOfMove()).getMax());
    }
}
