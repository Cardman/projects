package cards.belote.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class DeclaringPlayerValueValue implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt((((DeclaringPlayerValueStruct)_instance).getDeclaringPlayerValue()).getValue());
    }
}
