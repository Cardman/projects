package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class DifficultyCommonBeanIvPlayerGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).getIvPlayer());
    }
}
