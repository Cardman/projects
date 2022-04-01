package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class SelectItemBeanTypedPriceSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (SelectItemBean) ((PokemonBeanStruct)_instance).getInstance()).setTypedPrice(NumParsers.getString(_args[0]).getInstance());
        return NullStruct.NULL_VALUE;
    }
}
