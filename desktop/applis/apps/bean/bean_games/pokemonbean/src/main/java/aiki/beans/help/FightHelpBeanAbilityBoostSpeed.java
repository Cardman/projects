package aiki.beans.help;

import aiki.beans.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightHelpBeanAbilityBoostSpeed implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).abilityBoostSpeed(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
