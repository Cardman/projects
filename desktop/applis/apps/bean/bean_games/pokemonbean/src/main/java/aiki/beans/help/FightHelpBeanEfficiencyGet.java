package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightHelpBeanEfficiencyGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getTpDuoRate(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getEfficiency());
    }
}
