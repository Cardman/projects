package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;

public class EffectEndRoundBeanClickAbility implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectEndRoundBean) ((PokemonBeanStruct)_instance).getInstance()).clickAbility());
    }
}
