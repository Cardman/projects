package aiki.beans.pokemon.evolutions;

import aiki.beans.*;
import code.bean.nat.*;
public class EvolutionItemBeanItemGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EvolutionItemBean) ((PokemonBeanStruct)_instance).getInstance()).getItem().getTranslation());
    }
}
