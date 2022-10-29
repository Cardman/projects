package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class DifficultyCommonBeanSkipLearningMovesWhileNotGrowingLevelSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).setSkipLearningMovesWhileNotGrowingLevel(BooleanStruct.isTrue(_args[0]));
        return NullStruct.NULL_VALUE;
    }
}
