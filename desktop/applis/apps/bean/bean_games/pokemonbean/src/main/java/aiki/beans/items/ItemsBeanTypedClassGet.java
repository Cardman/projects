package aiki.beans.items;

import aiki.beans.*;
import code.bean.nat.*;
public class ItemsBeanTypedClassGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (ItemsBean) ((PokemonBeanStruct)_instance).getInstance()).getTypedClass().tryRet());
    }
}
