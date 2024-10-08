package aiki.beans.help;

import aiki.beans.*;
import code.bean.nat.*;
public class GeneralHelpBeanMiniMapGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getWcStr(( (GeneralHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getMiniMap());
    }
}
