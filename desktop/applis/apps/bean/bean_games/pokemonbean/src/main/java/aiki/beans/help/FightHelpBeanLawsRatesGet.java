package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightHelpBeanLawsRatesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getBigNatMap(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getLawsRates());
    }
}
