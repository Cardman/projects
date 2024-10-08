package aiki.beans.items;

import aiki.beans.*;
import code.bean.nat.*;
public class ItemsBeanGetMiniImage implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (ItemsBean) ((PokemonBeanStruct)_instance).getInstance()).getMiniImage(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
