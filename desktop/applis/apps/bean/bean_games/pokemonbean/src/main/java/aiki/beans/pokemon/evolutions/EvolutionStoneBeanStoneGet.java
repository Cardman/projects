package aiki.beans.pokemon.evolutions;

import aiki.beans.*;
import code.bean.nat.*;
public class EvolutionStoneBeanStoneGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EvolutionStoneBean) ((PokemonBeanStruct)_instance).getInstance()).getStone().getTranslation());
    }
}
