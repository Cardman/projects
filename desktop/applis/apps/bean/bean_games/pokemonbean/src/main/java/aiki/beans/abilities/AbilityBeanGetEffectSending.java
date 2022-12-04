package aiki.beans.abilities;

import aiki.beans.EffectWhileSendingWithStatisticStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class AbilityBeanGetEffectSending implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new EffectWhileSendingWithStatisticStruct(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getEffectSending());
    }
}
