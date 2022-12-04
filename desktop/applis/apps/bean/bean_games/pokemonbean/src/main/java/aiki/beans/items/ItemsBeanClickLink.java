package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class ItemsBeanClickLink implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (ItemsBean) ((PokemonBeanStruct)_instance).getInstance()).clickLink(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
