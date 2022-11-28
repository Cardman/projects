package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.structs.*;
public class EffectGlobalBeanClickInvokedMove implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EffectGlobalBean) ((PokemonBeanStruct)_instance).getInstance()).clickInvokedMove(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
