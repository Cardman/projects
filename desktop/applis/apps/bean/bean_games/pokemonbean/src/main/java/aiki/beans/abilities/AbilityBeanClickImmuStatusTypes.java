package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;

public class AbilityBeanClickImmuStatusTypes implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).clickImmuStatusTypes(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
