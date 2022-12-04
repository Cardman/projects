package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class ItemsBeanGetMiniImage implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (ItemsBean) ((PokemonBeanStruct)_instance).getInstance()).getMiniImage(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
