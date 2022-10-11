package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EffectMultSufferedMovePowerBeanGetTrType implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EffectMultMovePowerBean) ((PokemonBeanStruct)_instance).getInstance()).getTrType(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
