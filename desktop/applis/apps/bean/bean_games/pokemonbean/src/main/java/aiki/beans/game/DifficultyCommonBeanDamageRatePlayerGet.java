package aiki.beans.game;

import aiki.beans.*;
import code.bean.nat.*;
import code.expressionlanguage.structs.*;
public class DifficultyCommonBeanDamageRatePlayerGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).getDamageRatePlayer());
    }
}
