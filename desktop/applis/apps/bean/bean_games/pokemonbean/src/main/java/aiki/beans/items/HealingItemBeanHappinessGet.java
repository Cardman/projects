package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class HealingItemBeanHappinessGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getStrShort(( (HealingItemBean) ((PokemonBeanStruct)_instance).getInstance()).getHappiness());
    }
}
