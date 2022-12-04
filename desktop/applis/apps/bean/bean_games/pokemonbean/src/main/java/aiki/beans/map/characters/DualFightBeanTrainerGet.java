package aiki.beans.map.characters;

import aiki.beans.PersonStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class DualFightBeanTrainerGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new PersonStruct(( (DualFightBean) ((PokemonBeanStruct)_instance).getInstance()).getTrainer());
    }
}
