package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class DifficultyCommonBeanIvPlayerSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).setIvPlayer(NumParsers.convertToNumber(_args[0]).intStruct());
        return NullStruct.NULL_VALUE;
    }
}
