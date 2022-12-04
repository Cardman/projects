package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
public class AbilityBeanRecoilDamageFoeGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getRecoilDamageFoe());
    }
}
