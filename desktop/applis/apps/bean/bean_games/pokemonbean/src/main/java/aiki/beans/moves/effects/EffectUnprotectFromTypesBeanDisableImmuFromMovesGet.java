package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectUnprotectFromTypesBeanDisableImmuFromMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (EffectUnprotectFromTypesBean) ((PokemonBeanStruct)_instance).getInstance()).getDisableImmuFromMoves());
    }
}
