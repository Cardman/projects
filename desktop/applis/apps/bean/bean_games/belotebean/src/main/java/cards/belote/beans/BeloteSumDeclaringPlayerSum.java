package cards.belote.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class BeloteSumDeclaringPlayerSum implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt((((BeloteSumDeclaringPlayerStruct)_instance).getSumDeclaringPlayer()).getSum());
    }
}
