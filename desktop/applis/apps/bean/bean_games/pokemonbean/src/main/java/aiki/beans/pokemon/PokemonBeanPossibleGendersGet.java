package aiki.beans.pokemon;

import aiki.beans.*;
import code.bean.nat.*;
public class PokemonBeanPossibleGendersGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getValues(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getPossibleGenders());
    }
}
