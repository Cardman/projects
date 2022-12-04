package aiki.beans;

import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
public class RateAbsNb implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct(( ((RateStruct) _instance).getInstance()).absNb());
    }
}
