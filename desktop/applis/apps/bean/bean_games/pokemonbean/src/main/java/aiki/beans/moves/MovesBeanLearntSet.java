package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.WithFilterBean;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class MovesBeanLearntSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (WithFilterBean) ((PokemonBeanStruct)_instance).getInstance()).setLearnt(NumParsers.getString(_args[0]).getInstance());
        return NullStruct.NULL_VALUE;
    }
}
