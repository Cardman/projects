package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class EffectEndRoundIndividualBeanIsType implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (EffectEndRoundIndividualBean) ((PokemonBeanStruct)_instance).getInstance()).isType(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
