package aiki.beans.game;

import aiki.beans.*;
import code.bean.nat.*;
public class PokemonPlayerBeanEvolutionsGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrImg(( (PokemonPlayerBean) ((PokemonBeanStruct)_instance).getInstance()).getEvolutions());
    }
}
