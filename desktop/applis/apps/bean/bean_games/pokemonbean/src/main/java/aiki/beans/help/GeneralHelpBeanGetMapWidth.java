package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class GeneralHelpBeanGetMapWidth implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (GeneralHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getMapWidth());
    }
}
