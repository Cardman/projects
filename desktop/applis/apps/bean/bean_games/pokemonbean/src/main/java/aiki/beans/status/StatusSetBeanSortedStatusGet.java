package aiki.beans.status;

import aiki.beans.*;
import code.bean.nat.*;
public class StatusSetBeanSortedStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (StatusSetBean) ((PokemonBeanStruct)_instance).getInstance()).getSortedStatus());
    }
}
