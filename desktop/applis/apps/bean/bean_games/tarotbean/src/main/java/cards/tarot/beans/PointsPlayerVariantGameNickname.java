package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class PointsPlayerVariantGameNickname implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt((((PointsPlayerVariantGameStruct)_instance).getPointsPlayerVariantGame()).getNickname());
    }
}
