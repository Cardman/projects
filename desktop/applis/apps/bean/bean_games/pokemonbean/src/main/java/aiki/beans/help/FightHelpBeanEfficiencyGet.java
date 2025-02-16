package aiki.beans.help;

import aiki.beans.*;
import code.bean.nat.*;
public class FightHelpBeanEfficiencyGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getWeatherTypeRateMap(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getEfficiency());
    }
}
