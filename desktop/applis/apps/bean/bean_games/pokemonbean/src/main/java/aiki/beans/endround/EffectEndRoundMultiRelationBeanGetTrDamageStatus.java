package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectEndRoundMultiRelationBeanGetTrDamageStatus implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectEndRoundMultiRelationBean) ((PokemonBeanStruct)_instance).getInstance()).getTrDamageStatus(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
