package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
import code.expressionlanguage.structs.*;
public class SelectPokemonBeanHasEvoGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (SelectPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getHasEvo());
    }
}
