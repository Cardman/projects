package aiki.beans.abilities;

import aiki.beans.*;
import code.bean.nat.*;
public class AbilitiesBeanTypedAbilitySet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (AbilitiesBean) ((PokemonBeanStruct)_instance).getInstance()).getTypedAbility().setupValue(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
