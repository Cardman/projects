package aiki.beans.effects;

import aiki.beans.*;
import code.bean.nat.*;

public class EffectWhileSendingBeanCopyBoostGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getValues(((EffectWhileSendingBean) ((PokemonBeanStruct) _instance).getInstance()).getEffectStatisticCommon().getCopyBoost());
    }
}
