package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SelectItemBeanTypedPriceSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SelectItemBean) ((PokemonBeanStruct)_instance).getInstance()).getTypedPrice().setupValue(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
