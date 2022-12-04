package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AbilitiesBeanTypedAbilitySet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (AbilitiesBean) ((PokemonBeanStruct)_instance).getInstance()).setTypedAbility(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
