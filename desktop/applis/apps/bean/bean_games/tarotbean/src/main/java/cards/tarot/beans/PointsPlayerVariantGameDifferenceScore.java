package cards.tarot.beans;

import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public final class PointsPlayerVariantGameDifferenceScore implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct((((PointsPlayerVariantGameStruct)_instance).getPointsPlayerVariantGame()).getDifferenceScore(), TarotStandards.TYPE_RATE);
    }
}
