package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class DifficultyBeanDiffWinningExpPtsFightSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (DifficultyBean) ((PokemonBeanStruct)_instance).getInstance()).getDifficultyCommon().setDiffWinningExpPtsFight(NumParsers.getString(_args[0]).getInstance());
        return NullStruct.NULL_VALUE;
    }
}
