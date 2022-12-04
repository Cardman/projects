package aiki.beans;

import aiki.map.pokemon.WildPk;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class WildPkGetItem implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (WildPk) ((PkStruct)_instance).getWildPk()).getItem());
    }
}
