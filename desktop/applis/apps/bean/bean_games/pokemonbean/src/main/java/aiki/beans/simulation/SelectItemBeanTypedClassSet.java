package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class SelectItemBeanTypedClassSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (SelectItemBean) ((PokemonBeanStruct)_instance).getInstance()).setTypedClass(NumParsers.getString(_args[0]).getInstance());
        return NullStruct.NULL_VALUE;
    }
}
