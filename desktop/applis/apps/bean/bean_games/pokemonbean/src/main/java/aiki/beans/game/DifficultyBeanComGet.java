package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.WithDifficultyCommon;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;

public class DifficultyBeanComGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new DifficultyCommonStruct(((WithDifficultyCommon)((PokemonBeanStruct)_instance).getInstance()).getDifficultyCommon());
    }
}
