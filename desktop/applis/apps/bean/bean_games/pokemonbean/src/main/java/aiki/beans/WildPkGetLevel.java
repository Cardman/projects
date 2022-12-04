package aiki.beans;

import aiki.map.pokemon.WildPk;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class WildPkGetLevel implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (WildPk) ((PkStruct)_instance).getWildPk()).getLevel());
    }
}
