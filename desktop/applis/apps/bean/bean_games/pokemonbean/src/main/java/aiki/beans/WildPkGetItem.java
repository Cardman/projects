package aiki.beans;

import code.bean.nat.*;
public class WildPkGetItem implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt((((PkStruct)_instance).getWildPk()).getItem());
    }
}
