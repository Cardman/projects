package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class ItemBeanPriceGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (ItemBean) ((PokemonBeanStruct)_instance).getInstance()).getPrice());
    }
}
