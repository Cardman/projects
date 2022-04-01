package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class PokemonBeanTypesGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getTypes());
    }
}
