package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;

public class EffectSwitchAbilitiesBeanGetTrAbility implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectSwitchAbilitiesBean) ((PokemonBeanStruct)_instance).getInstance()).getTrAbility());
    }
}
