package aiki.beans.help;

import aiki.beans.*;
import code.bean.nat.*;
public class FightHelpBeanAutoDamageGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getAutoDamage());
    }
}
