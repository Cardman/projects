package aiki.beans;

import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
import code.bean.nat.*;
public class RateIsZeroOrGt implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( ((RtSt) _instance).getInstance()).isZeroOrGt());
    }
}
