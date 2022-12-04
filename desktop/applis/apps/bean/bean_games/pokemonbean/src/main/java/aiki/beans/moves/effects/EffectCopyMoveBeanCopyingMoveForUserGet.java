package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectCopyMoveBeanCopyingMoveForUserGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (EffectCopyMoveBean) ((PokemonBeanStruct)_instance).getInstance()).getCopyingMoveForUser());
    }
}
