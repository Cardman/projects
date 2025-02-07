package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectCloneBeanMovesBatonPassGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (EffectCloneBean) ((PokemonBeanStruct)_instance).getInstance()).getMovesBatonPass());
    }
}
