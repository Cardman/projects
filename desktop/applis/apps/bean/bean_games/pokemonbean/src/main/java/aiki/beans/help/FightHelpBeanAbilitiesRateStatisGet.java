package aiki.beans.help;

import aiki.beans.*;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightHelpBeanAbilitiesRateStatisGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getAbilitiesRateStatis());
    }
}
