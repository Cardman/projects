package aiki.beans.help;

import aiki.beans.*;
import code.bean.nat.*;
public class GeneralHelpBeanGetMovesAtLevel implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (GeneralHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getMovesAtLevelFirstPk());
    }
}
