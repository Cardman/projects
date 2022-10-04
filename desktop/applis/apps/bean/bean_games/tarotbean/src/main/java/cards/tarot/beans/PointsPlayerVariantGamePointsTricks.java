package cards.tarot.beans;

import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public final class PointsPlayerVariantGamePointsTricks implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct((((PointsPlayerVariantGameStruct)_instance).getPointsPlayerVariantGame()).getPointsTricks(), TarotStandards.TYPE_RATE);
    }
}
