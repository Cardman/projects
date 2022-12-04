package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class ItemBeanNameSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (ItemBean) ((PokemonBeanStruct)_instance).getInstance()).setName(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
