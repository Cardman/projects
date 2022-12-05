package aiki.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class TypesDuoGetDamageType implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( ((TypesDuoStruct) _instance).getTypesDuo()).getDamageType());
    }
}
