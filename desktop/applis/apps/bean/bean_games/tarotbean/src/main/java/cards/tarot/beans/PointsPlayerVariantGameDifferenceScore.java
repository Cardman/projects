package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public final class PointsPlayerVariantGameDifferenceScore implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt((((PointsPlayerVariantGameStruct)_instance).getPointsPlayerVariantGame()).getDifferenceScore());
    }
}
