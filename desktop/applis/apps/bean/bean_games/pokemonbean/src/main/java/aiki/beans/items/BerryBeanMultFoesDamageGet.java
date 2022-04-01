package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class BerryBeanMultFoesDamageGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getEffRateStr(( (BerryBean) ((PokemonBeanStruct)_instance).getInstance()).getMultFoesDamage());
    }
}
