package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class EffectEndRoundGlobalBeanPuttingKoGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (EffectEndRoundGlobalBean) ((PokemonBeanStruct)_instance).getInstance()).getPuttingKo());
    }
}
