package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EffectEndRoundMultiRelationBeanGetTrDamageStatus implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EffectEndRoundMultiRelationBean) ((PokemonBeanStruct)_instance).getInstance()).getTrDamageStatus(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
