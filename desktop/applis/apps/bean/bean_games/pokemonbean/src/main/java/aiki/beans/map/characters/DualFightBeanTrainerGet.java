package aiki.beans.map.characters;

import aiki.beans.PersonStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class DualFightBeanTrainerGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new PersonStruct(( (DualFightBean) ((PokemonBeanStruct)_instance).getInstance()).getTrainer());
    }
}
