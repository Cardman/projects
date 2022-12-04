package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class AbilityBeanMultStatIfDamgeTypeGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStatisticTypeByteMap(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getMultStatIfDamgeType());
    }
}
