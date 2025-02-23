package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SelectItemBeanTypedClassSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SelectItemBean) ((PokemonBeanStruct)_instance).getInstance()).getTypedClass().setupValue(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
