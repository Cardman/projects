package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class AbilityBeanGetTrDivideStatusRoundKey implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getTrDivideStatusRoundKey(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
