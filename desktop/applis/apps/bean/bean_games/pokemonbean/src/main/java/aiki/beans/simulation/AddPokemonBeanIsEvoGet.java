package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
public class AddPokemonBeanIsEvoGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanLgNames.wrapStd(( (AddPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getIsEvo());
    }
}
