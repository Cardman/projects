package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class StatusSetBeanSortedStatusGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(( (StatusSetBean) ((PokemonBeanStruct)_instance).getInstance()).getSortedStatus());
    }
}
