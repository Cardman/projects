package aiki.beans.status;

import aiki.beans.*;
import code.bean.nat.*;
public class StatusSetBeanTypedStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (StatusSetBean) ((PokemonBeanStruct)_instance).getInstance()).getTypedStatus().tryRet());
    }
}
