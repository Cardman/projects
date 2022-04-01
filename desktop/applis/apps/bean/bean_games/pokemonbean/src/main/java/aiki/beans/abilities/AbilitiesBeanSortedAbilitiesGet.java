package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class AbilitiesBeanSortedAbilitiesGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(( (AbilitiesBean) ((PokemonBeanStruct)_instance).getInstance()).getSortedAbilities());
    }
}
