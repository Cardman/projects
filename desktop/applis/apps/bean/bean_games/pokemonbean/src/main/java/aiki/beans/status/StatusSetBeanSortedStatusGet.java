package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class StatusSetBeanSortedStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (StatusSetBean) ((PokemonBeanStruct)_instance).getInstance()).getSortedStatus());
    }
}
