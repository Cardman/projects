package aiki.beans.map.pokemon;

import aiki.beans.PersonStruct;
import aiki.beans.PokemonBeanStruct;
import aiki.map.characters.Trainer;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonTeamBeanTrainerSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (PokemonTeamBean) ((PokemonBeanStruct)_instance).getInstance()).setTrainer((Trainer) ((PersonStruct)_args[0]).getPerson());
        return NaNu.NULL_VALUE;
    }
}
