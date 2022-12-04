package aiki.beans.pokemon;

import aiki.beans.*;
import code.bean.nat.*;
import code.expressionlanguage.structs.*;
public class PokedexBeanIsLegGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (PokedexBean) ((PokemonBeanStruct)_instance).getInstance()).getIsLeg());
    }
}
