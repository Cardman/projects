package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class EditPokemonBeanExperienceGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (EditPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getExperience());
    }
}
