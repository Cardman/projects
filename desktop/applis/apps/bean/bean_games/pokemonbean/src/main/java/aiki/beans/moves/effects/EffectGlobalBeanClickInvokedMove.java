package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;

public class EffectGlobalBeanClickInvokedMove implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_instance).getInstance()).clickInvokedMove());
    }
}
