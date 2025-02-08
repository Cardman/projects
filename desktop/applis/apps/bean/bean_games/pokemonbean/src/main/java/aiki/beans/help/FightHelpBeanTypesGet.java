package aiki.beans.help;

import aiki.beans.*;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightHelpBeanTypesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getValues(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getTypes());
    }
}
