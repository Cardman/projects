package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectSwitchPointViewBeanThieveBonus implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (EffectSwitchPointViewBean) ((PokemonBeanStruct)_instance).getInstance()).thieveBonus());
    }
}
