package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectBeanMoveSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EffectBean) ((PokemonBeanStruct)_instance).getInstance()).setMove(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
