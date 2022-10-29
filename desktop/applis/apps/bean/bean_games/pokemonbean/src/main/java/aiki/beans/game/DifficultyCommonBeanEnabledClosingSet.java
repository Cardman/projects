package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class DifficultyCommonBeanEnabledClosingSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).setEnabledClosing(BooleanStruct.isTrue(_args[0]));
        return NullStruct.NULL_VALUE;
    }
}
