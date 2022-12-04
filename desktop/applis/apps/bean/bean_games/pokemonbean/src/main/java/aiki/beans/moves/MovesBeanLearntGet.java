package aiki.beans.moves;

import aiki.beans.*;
import code.bean.nat.*;
import code.expressionlanguage.structs.*;
public class MovesBeanLearntGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (WithFilterBean) ((PokemonBeanStruct)_instance).getInstance()).getLearnt());
    }
}
