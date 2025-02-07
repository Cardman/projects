package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectCopyMoveBeanMovesTransformingGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (EffectCopyMoveBean) ((PokemonBeanStruct)_instance).getInstance()).getMovesTransforming());
    }
}
