package aiki.beans.help;

import aiki.beans.*;
import code.bean.nat.*;
public class GeneralHelpBeanTypesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (GeneralHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getTypes());
    }
}
