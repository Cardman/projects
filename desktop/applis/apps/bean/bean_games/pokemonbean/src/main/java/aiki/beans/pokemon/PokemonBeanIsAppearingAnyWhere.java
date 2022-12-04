package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonBeanIsAppearingAnyWhere implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).isAppearingAnyWhere());
    }
}
