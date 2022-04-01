package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class HealingStatusBeanGetTrStatus implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (HealingStatusBean) ((PokemonBeanStruct)_instance).getInstance()).getTrStatus(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
