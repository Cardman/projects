package aiki.beans.items;

import aiki.beans.*;
import code.bean.nat.*;
public class ItemsBeanTypedClassSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (ItemsBean) ((PokemonBeanStruct)_instance).getInstance()).getTypedClass().setupValue(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
