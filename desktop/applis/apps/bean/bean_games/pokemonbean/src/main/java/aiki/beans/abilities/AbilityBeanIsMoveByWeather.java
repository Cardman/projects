package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class AbilityBeanIsMoveByWeather implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).isMoveByWeather(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
