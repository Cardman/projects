package aiki.beans.fight;

import aiki.beans.FighterNamePkNameMvStruct;
import code.bean.nat.NaSt;
import code.bean.nat.NaStSt;
import code.bean.nat.NatCaller;

public class FighterNamePkNameMvNameMvGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(((FighterNamePkNameMvStruct)_instance).getKey().getNameMvTr());
    }
}
