package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class CombosBeanGetCombosKey implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrList(( (CombosBean) ((PokemonBeanStruct)_instance).getInstance()).getCombosKey());
    }
}
