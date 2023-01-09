package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class EffectEndRoundStatusRelationBeanThievedHpRateTargetToUserGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (EffectEndRoundStatusRelationBean) ((PokemonBeanStruct)_instance).getInstance()).getThievedHpRateTargetToUser());
    }
}
