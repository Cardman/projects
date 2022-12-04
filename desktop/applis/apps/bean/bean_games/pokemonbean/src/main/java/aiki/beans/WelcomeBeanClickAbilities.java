package aiki.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class WelcomeBeanClickAbilities implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (WelcomeBean) ((PokemonBeanStruct)_instance).getInstance()).clickAbilities());
    }
}
