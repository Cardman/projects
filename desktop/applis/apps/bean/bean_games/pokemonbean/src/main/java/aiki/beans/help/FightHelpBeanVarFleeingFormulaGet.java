package aiki.beans.help;

import aiki.beans.*;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightHelpBeanVarFleeingFormulaGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStr(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getVarFleeingFormula());
    }
}
