package aiki.beans.moves;

import aiki.beans.*;
import code.bean.nat.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.structs.*;

public class MovesBeanClickLink implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (MovesBean) ((PokemonBeanStruct)_instance).getInstance()).clickLink(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
