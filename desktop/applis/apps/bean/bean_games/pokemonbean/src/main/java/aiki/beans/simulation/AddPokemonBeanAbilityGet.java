package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class AddPokemonBeanAbilityGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AddPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getAbility().tryRet());
    }
}
