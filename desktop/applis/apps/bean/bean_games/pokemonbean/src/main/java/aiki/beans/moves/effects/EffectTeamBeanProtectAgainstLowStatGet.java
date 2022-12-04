package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectTeamBeanProtectAgainstLowStatGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (EffectTeamBean) ((PokemonBeanStruct)_instance).getInstance()).getProtectAgainstLowStat());
    }
}
