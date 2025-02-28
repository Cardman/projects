package aiki.beans.help;

import aiki.beans.*;
import code.bean.nat.*;
public class GeneralHelpBeanTmGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStrOnly(( (GeneralHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getTm());
    }
}
