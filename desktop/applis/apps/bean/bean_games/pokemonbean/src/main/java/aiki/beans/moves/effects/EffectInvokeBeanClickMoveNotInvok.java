package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;

public class EffectInvokeBeanClickMoveNotInvok implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectInvokeBean) ((PokemonBeanStruct)_instance).getInstance()).clickMoveNotInvok(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
