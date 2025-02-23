package aiki.beans.items;

import aiki.beans.*;
import code.bean.nat.*;
public class ItemsBeanTypedPriceGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (ItemsBean) ((PokemonBeanStruct)_instance).getInstance()).getTypedPrice().tryRet());
    }
}
