package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class EffectBeanMoveSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (EffectBean) ((PokemonBeanStruct)_instance).getInstance()).setMove(NumParsers.getString(_args[0]).getInstance());
        return NullStruct.NULL_VALUE;
    }
}
