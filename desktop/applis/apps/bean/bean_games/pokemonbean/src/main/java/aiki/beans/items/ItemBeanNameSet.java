package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class ItemBeanNameSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (ItemBean) ((PokemonBeanStruct)_instance).getInstance()).setName(NumParsers.getString(_args[0]).getInstance());
        return NullStruct.NULL_VALUE;
    }
}
