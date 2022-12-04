package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.LgIntStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonBeanHatchingStepsGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new LgIntStruct(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getHatchingSteps());
    }
}
