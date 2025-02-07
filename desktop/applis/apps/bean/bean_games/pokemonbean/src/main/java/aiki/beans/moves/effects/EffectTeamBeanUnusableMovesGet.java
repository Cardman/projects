package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectTeamBeanUnusableMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (EffectTeamBean) ((PokemonBeanStruct)_instance).getInstance()).getUnusableMoves());
    }
}
