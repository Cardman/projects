package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SelectItemBeanTypedPriceGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SelectItemBean) ((PokemonBeanStruct)_instance).getInstance()).getTypedPrice().tryRet());
    }
}
