package aiki.beans.help;

import aiki.beans.*;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightHelpBeanBoostsGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getLongRate(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getBoosts());
    }
}
