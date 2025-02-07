package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectInvokeBeanGlobalMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (EffectInvokeBean) ((PokemonBeanStruct)_instance).getInstance()).getGlobalMoves());
    }
}
