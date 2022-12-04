package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectWhileSendingBeanMapVarsFailGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStr(( (EffectWhileSendingBean) ((PokemonBeanStruct)_instance).getInstance()).getMapVarsFail());
    }
}
