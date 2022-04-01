package aiki.beans.map.pokemon;

import aiki.beans.PersonStruct;
import aiki.beans.PokemonBeanStruct;
import aiki.map.characters.Trainer;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonTeamBeanTrainerSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (PokemonTeamBean) ((PokemonBeanStruct)_instance).getInstance()).setTrainer((Trainer) ((PersonStruct)_args[0]).getInstance());
        return NullStruct.NULL_VALUE;
    }
}
