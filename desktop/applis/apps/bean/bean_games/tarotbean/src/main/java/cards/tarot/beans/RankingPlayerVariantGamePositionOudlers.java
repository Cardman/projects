package cards.tarot.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class RankingPlayerVariantGamePositionOudlers implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct((((RankingPlayerVariantGameStruct)_instance).getRankingPlayerVariantGame()).getPositionOudlers());
    }
}
