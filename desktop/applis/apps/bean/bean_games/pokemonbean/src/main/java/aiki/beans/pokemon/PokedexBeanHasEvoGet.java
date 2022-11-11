package aiki.beans.pokemon;

import aiki.beans.*;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;

public class PokedexBeanHasEvoGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanLgNames.wrapStd(( (WithFilterBean) ((PokemonBeanStruct)_instance).getInstance()).getHasEvo());
    }
}
