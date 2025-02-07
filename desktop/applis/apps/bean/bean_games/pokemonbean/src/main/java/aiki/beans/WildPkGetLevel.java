package aiki.beans;

import code.bean.nat.*;
public class WildPkGetLevel implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt((((PkStruct)_instance).getWildPk()).getLevel());
    }
}
