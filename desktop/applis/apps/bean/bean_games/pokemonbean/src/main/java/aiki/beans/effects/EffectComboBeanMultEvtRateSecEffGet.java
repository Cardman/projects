package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class EffectComboBeanMultEvtRateSecEffGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (EffectComboBean) ((PokemonBeanStruct)_instance).getInstance()).getMultEvtRateSecEff());
    }
}
