package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectCloneBeanMovesEndRoundGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (EffectCloneBean) ((PokemonBeanStruct)_instance).getInstance()).getMovesEndRound());
    }
}
