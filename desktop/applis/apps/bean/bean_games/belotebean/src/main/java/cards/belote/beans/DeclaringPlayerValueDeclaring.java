package cards.belote.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class DeclaringPlayerValueDeclaring implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt((((DeclaringPlayerValueStruct)_instance).getDeclaringPlayerValue()).getDeclaring());
    }
}
