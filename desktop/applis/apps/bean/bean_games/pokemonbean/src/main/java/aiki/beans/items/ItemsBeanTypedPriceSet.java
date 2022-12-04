package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class ItemsBeanTypedPriceSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (ItemsBean) ((PokemonBeanStruct)_instance).getInstance()).setTypedPrice(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
