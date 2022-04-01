package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class AbilityBeanGetTrImmuMoveByWeather implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getTrImmuMoveByWeather(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
