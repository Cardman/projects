package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonBeanAbilitiesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getAbilities());
    }
}
