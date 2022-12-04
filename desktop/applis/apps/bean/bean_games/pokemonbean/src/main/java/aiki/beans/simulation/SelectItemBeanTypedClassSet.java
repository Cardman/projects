package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SelectItemBeanTypedClassSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SelectItemBean) ((PokemonBeanStruct)_instance).getInstance()).setTypedClass(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
