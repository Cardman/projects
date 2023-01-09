package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class EffectEndRoundGlobalBeanDamageEndRoundGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (EffectEndRoundGlobalBean) ((PokemonBeanStruct)_instance).getInstance()).getDamageEndRound());
    }
}
