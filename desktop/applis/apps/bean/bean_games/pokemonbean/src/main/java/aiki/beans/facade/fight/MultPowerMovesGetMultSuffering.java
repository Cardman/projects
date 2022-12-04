package aiki.beans.facade.fight;

import aiki.beans.fight.MultPowerMovesStruct;
import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
public class MultPowerMovesGetMultSuffering implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct(( ((MultPowerMovesStruct) _instance).getInstance()).getMultSuffering());
    }
}
