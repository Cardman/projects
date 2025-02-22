package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SelectAbilityBeanTypedAbilitySet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SelectAbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getTypedAbility().setupValue(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
