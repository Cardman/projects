package aiki.beans.abilities;

import aiki.beans.*;
import code.bean.nat.*;
public class AbilitiesBeanTypedAbilityGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AbilitiesBean) ((PokemonBeanStruct)_instance).getInstance()).getTypedAbility().tryRet());
    }
}
