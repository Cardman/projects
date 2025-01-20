package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
public class FighterBeanStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrLong(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getStatus());
    }
}
