package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class AbilitiesBeanTypedAbilitySet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (AbilitiesBean) ((PokemonBeanStruct)_instance).getInstance()).setTypedAbility(NumParsers.getString(_args[0]).getInstance());
        return NullStruct.NULL_VALUE;
    }
}
