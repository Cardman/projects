package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class EditPokemonBeanExperienceGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (EditPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getExperience(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
