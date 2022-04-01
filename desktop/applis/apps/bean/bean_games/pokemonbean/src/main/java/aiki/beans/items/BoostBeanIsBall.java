package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class BoostBeanIsBall implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (BoostBean) ((PokemonBeanStruct)_instance).getInstance()).isBall(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
