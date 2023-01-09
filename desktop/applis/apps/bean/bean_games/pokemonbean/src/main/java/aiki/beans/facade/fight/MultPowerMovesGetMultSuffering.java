package aiki.beans.facade.fight;

import aiki.beans.fight.MultPowerMovesStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class MultPowerMovesGetMultSuffering implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( ((MultPowerMovesStruct) _instance).getMultPowerMoves()).getMultSuffering());
    }
}
