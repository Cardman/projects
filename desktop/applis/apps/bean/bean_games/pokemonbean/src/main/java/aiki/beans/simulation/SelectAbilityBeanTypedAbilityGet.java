package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SelectAbilityBeanTypedAbilityGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SelectAbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getTypedAbility().tryRet());
    }
}
