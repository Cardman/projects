package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class HealingStatusBeanClickStatus implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (HealingStatusBean) ((PokemonBeanStruct)_instance).getInstance()).clickStatus(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
