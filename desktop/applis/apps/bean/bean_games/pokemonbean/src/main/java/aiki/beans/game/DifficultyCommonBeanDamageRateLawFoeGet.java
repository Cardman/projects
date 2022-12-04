package aiki.beans.game;

import aiki.beans.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class DifficultyCommonBeanDamageRateLawFoeGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).getDamageRateLawFoe());
    }
}
