package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectGlobalBeanMovesUsedByTargetedFightersGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (EffectGlobalBean) ((PokemonBeanStruct)_instance).getInstance()).getMovesUsedByTargetedFighters());
    }
}
