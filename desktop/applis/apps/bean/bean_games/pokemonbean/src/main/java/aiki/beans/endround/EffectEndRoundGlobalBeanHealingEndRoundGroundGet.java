package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
public class EffectEndRoundGlobalBeanHealingEndRoundGroundGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct(( (EffectEndRoundGlobalBean) ((PokemonBeanStruct)_instance).getInstance()).getHealingEndRoundGround());
    }
}
