package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class AbilityBeanBreakFoeImmuneGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getTypesDuo(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getBreakFoeImmune());
    }
}
