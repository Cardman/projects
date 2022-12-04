package aiki.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AreaApparitionGetAvgNbSteps implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( ((AreaApparitionStruct) _instance).getWildPk()).getAvgNbSteps());
    }
}
