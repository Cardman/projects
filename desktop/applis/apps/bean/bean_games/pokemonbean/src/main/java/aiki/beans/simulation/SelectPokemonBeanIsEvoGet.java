package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
import code.expressionlanguage.structs.*;
public class SelectPokemonBeanIsEvoGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (SelectPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getIsEvo());
    }
}
