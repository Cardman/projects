package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class GeneralHelpBeanFirstPokemonHasItem implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (GeneralHelpBean) ((PokemonBeanStruct)_instance).getInstance()).firstPokemonHasItem());
    }
}
