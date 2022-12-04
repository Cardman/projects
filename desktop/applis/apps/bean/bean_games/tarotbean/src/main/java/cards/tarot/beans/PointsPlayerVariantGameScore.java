package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class PointsPlayerVariantGameScore implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt((((PointsPlayerVariantGameStruct)_instance).getPointsPlayerVariantGame()).getScore());
    }
}
