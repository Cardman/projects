package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class EffectStatusBeanIsStatus implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (EffectStatusBean) ((PokemonBeanStruct)_instance).getInstance()).isStatus(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
